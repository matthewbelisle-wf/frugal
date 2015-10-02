// Autogenerated by Frugal Compiler (0.0.1)
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

package event

import (
	"fmt"
	"log"

	"git.apache.org/thrift.git/lib/go/thrift"
	"github.com/Workiva/frugal-go"
)

const delimiter = "."

type EventsPublisher struct {
	Transport frugal.Transport
	Protocol  thrift.TProtocol
	SeqId     int32
}

func NewEventsPublisher(t frugal.TransportFactory, f thrift.TTransportFactory, p thrift.TProtocolFactory) *EventsPublisher {
	provider := frugal.NewProvider(t, f, p)
	transport, protocol := provider.New()
	return &EventsPublisher{
		Transport: transport,
		Protocol:  protocol,
		SeqId:     0,
	}
}

func (l *EventsPublisher) PublishEventCreated(user string, req *Event) error {
	op := "EventCreated"
	prefix := fmt.Sprintf("foo.%s.", user)
	topic := fmt.Sprintf("%sEvents%s%s", prefix, delimiter, op)
	l.Transport.PreparePublish(topic)
	oprot := l.Protocol
	l.SeqId++
	if err := oprot.WriteMessageBegin(op, thrift.CALL, l.SeqId); err != nil {
		return err
	}
	if err := req.Write(oprot); err != nil {
		return err
	}
	if err := oprot.WriteMessageEnd(); err != nil {
		return err
	}
	return oprot.Flush()
}

type EventsSubscriber struct {
	Provider *frugal.Provider
}

func NewEventsSubscriber(t frugal.TransportFactory, f thrift.TTransportFactory, p thrift.TProtocolFactory) *EventsSubscriber {
	provider := frugal.NewProvider(t, f, p)
	return &EventsSubscriber{Provider: provider}
}

func (l *EventsSubscriber) SubscribeEventCreated(user string, handler func(*Event)) (*frugal.Subscription, error) {
	op := "EventCreated"
	prefix := fmt.Sprintf("foo.%s.", user)
	topic := fmt.Sprintf("%sEvents%s%s", prefix, delimiter, op)
	transport, protocol := l.Provider.New()
	if err := transport.Subscribe(topic); err != nil {
		return nil, err
	}

	sub := frugal.NewSubscription(topic, transport)
	go func() {
		for {
			received, err := l.recvEventCreated(op, protocol)
			if err != nil {
				if e, ok := err.(thrift.TTransportException); ok && e.TypeId() == thrift.END_OF_FILE {
					return
				}
				log.Println("frugal: error receiving:", err)
				sub.Signal(err)
				sub.Unsubscribe()
				return
			}
			handler(received)
		}
	}()

	return sub, nil
}

func (l *EventsSubscriber) recvEventCreated(op string, iprot thrift.TProtocol) (*Event, error) {
	name, _, _, err := iprot.ReadMessageBegin()
	if err != nil {
		return nil, err
	}
	if name != op {
		iprot.Skip(thrift.STRUCT)
		iprot.ReadMessageEnd()
		x9 := thrift.NewTApplicationException(thrift.UNKNOWN_METHOD, "Unknown function "+name)
		return nil, x9
	}
	req := &Event{}
	if err := req.Read(iprot); err != nil {
		return nil, err
	}

	iprot.ReadMessageEnd()
	return req, nil
}