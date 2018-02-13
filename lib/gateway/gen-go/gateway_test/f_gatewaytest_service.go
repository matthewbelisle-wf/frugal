// Autogenerated by Frugal Compiler (2.14.0)
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

package gateway_test

import (
	"bytes"
	"fmt"

	"git.apache.org/thrift.git/lib/go/thrift"
	"github.com/Sirupsen/logrus"
	"github.com/Workiva/frugal/lib/go"
)

// (needed to ensure safety because of naive import list construction.)
var _ = thrift.ZERO
var _ = fmt.Printf
var _ = bytes.Equal
var _ = logrus.DebugLevel

type FGatewayTest interface {
	GetContainer(ctx frugal.FContext, baseType *BaseType) (r *ContainerType, err error)
}

type FGatewayTestClient struct {
	transport       frugal.FTransport
	protocolFactory *frugal.FProtocolFactory
	methods         map[string]*frugal.Method
}

func NewFGatewayTestClient(provider *frugal.FServiceProvider, middleware ...frugal.ServiceMiddleware) *FGatewayTestClient {
	methods := make(map[string]*frugal.Method)
	client := &FGatewayTestClient{
		transport:       provider.GetTransport(),
		protocolFactory: provider.GetProtocolFactory(),
		methods:         methods,
	}
	middleware = append(middleware, provider.GetMiddleware()...)
	methods["getContainer"] = frugal.NewMethod(client, client.getContainer, "getContainer", middleware)
	return client
}

func (f *FGatewayTestClient) GetContainer(ctx frugal.FContext, basetype *BaseType) (r *ContainerType, err error) {
	ret := f.methods["getContainer"].Invoke([]interface{}{ctx, basetype})
	if len(ret) != 2 {
		panic(fmt.Sprintf("Middleware returned %d arguments, expected 2", len(ret)))
	}
	if ret[0] != nil {
		r = ret[0].(*ContainerType)
	}
	if ret[1] != nil {
		err = ret[1].(error)
	}
	return r, err
}

func (f *FGatewayTestClient) getContainer(ctx frugal.FContext, basetype *BaseType) (r *ContainerType, err error) {
	buffer := frugal.NewTMemoryOutputBuffer(f.transport.GetRequestSizeLimit())
	oprot := f.protocolFactory.GetProtocol(buffer)
	if err = oprot.WriteRequestHeader(ctx); err != nil {
		return
	}
	if err = oprot.WriteMessageBegin("getContainer", thrift.CALL, 0); err != nil {
		return
	}
	args := GatewayTestGetContainerArgs{
		BaseType: basetype,
	}
	if err = args.Write(oprot); err != nil {
		return
	}
	if err = oprot.WriteMessageEnd(); err != nil {
		return
	}
	if err = oprot.Flush(); err != nil {
		return
	}
	var resultTransport thrift.TTransport
	resultTransport, err = f.transport.Request(ctx, buffer.Bytes())
	if err != nil {
		return
	}
	iprot := f.protocolFactory.GetProtocol(resultTransport)
	if err = iprot.ReadResponseHeader(ctx); err != nil {
		return
	}
	method, mTypeId, _, err := iprot.ReadMessageBegin()
	if err != nil {
		return
	}
	if method != "getContainer" {
		err = thrift.NewTApplicationException(frugal.APPLICATION_EXCEPTION_WRONG_METHOD_NAME, "getContainer failed: wrong method name")
		return
	}
	if mTypeId == thrift.EXCEPTION {
		error0 := thrift.NewTApplicationException(frugal.APPLICATION_EXCEPTION_UNKNOWN, "Unknown Exception")
		var error1 thrift.TApplicationException
		error1, err = error0.Read(iprot)
		if err != nil {
			return
		}
		if err = iprot.ReadMessageEnd(); err != nil {
			return
		}
		if error1.TypeId() == frugal.APPLICATION_EXCEPTION_RESPONSE_TOO_LARGE {
			err = thrift.NewTTransportException(frugal.TRANSPORT_EXCEPTION_RESPONSE_TOO_LARGE, error1.Error())
			return
		}
		err = error1
		return
	}
	if mTypeId != thrift.REPLY {
		err = thrift.NewTApplicationException(frugal.APPLICATION_EXCEPTION_INVALID_MESSAGE_TYPE, "getContainer failed: invalid message type")
		return
	}
	result := GatewayTestGetContainerResult{}
	if err = result.Read(iprot); err != nil {
		return
	}
	if err = iprot.ReadMessageEnd(); err != nil {
		return
	}
	r = result.GetSuccess()
	return
}

type FGatewayTestProcessor struct {
	*frugal.FBaseProcessor
}

func NewFGatewayTestProcessor(handler FGatewayTest, middleware ...frugal.ServiceMiddleware) *FGatewayTestProcessor {
	p := &FGatewayTestProcessor{frugal.NewFBaseProcessor()}
	p.AddToProcessorMap("getContainer", &gatewaytestFGetContainer{frugal.NewFBaseProcessorFunction(p.GetWriteMutex(), frugal.NewMethod(handler, handler.GetContainer, "GetContainer", middleware))})
	p.AddToAnnotationsMap("getContainer", map[string]string{
		"http.method":       "post",
		"http.pathTemplate": "/v1/container/",
	})
	return p
}

type gatewaytestFGetContainer struct {
	*frugal.FBaseProcessorFunction
}

func (p *gatewaytestFGetContainer) Process(ctx frugal.FContext, iprot, oprot *frugal.FProtocol) error {
	args := GatewayTestGetContainerArgs{}
	var err error
	if err = args.Read(iprot); err != nil {
		iprot.ReadMessageEnd()
		p.GetWriteMutex().Lock()
		err = gatewaytestWriteApplicationError(ctx, oprot, frugal.APPLICATION_EXCEPTION_PROTOCOL_ERROR, "getContainer", err.Error())
		p.GetWriteMutex().Unlock()
		return err
	}

	iprot.ReadMessageEnd()
	result := GatewayTestGetContainerResult{}
	var err2 error
	ret := p.InvokeMethod([]interface{}{ctx, args.BaseType})
	if len(ret) != 2 {
		panic(fmt.Sprintf("Middleware returned %d arguments, expected 2", len(ret)))
	}
	if ret[1] != nil {
		err2 = ret[1].(error)
	}
	if err2 != nil {
		if err3, ok := err2.(thrift.TApplicationException); ok {
			p.GetWriteMutex().Lock()
			oprot.WriteResponseHeader(ctx)
			oprot.WriteMessageBegin("getContainer", thrift.EXCEPTION, 0)
			err3.Write(oprot)
			oprot.WriteMessageEnd()
			oprot.Flush()
			p.GetWriteMutex().Unlock()
			return nil
		}
		p.GetWriteMutex().Lock()
		err2 := gatewaytestWriteApplicationError(ctx, oprot, frugal.APPLICATION_EXCEPTION_INTERNAL_ERROR, "getContainer", "Internal error processing getContainer: "+err2.Error())
		p.GetWriteMutex().Unlock()
		return err2
	} else {
		var retval *ContainerType = ret[0].(*ContainerType)
		result.Success = retval
	}
	p.GetWriteMutex().Lock()
	defer p.GetWriteMutex().Unlock()
	if err2 = oprot.WriteResponseHeader(ctx); err2 != nil {
		if frugal.IsErrTooLarge(err2) {
			gatewaytestWriteApplicationError(ctx, oprot, frugal.APPLICATION_EXCEPTION_RESPONSE_TOO_LARGE, "getContainer", err2.Error())
			return nil
		}
		err = err2
	}
	if err2 = oprot.WriteMessageBegin("getContainer", thrift.REPLY, 0); err2 != nil {
		if frugal.IsErrTooLarge(err2) {
			gatewaytestWriteApplicationError(ctx, oprot, frugal.APPLICATION_EXCEPTION_RESPONSE_TOO_LARGE, "getContainer", err2.Error())
			return nil
		}
		err = err2
	}
	if err2 = result.Write(oprot); err == nil && err2 != nil {
		if frugal.IsErrTooLarge(err2) {
			gatewaytestWriteApplicationError(ctx, oprot, frugal.APPLICATION_EXCEPTION_RESPONSE_TOO_LARGE, "getContainer", err2.Error())
			return nil
		}
		err = err2
	}
	if err2 = oprot.WriteMessageEnd(); err == nil && err2 != nil {
		if frugal.IsErrTooLarge(err2) {
			gatewaytestWriteApplicationError(ctx, oprot, frugal.APPLICATION_EXCEPTION_RESPONSE_TOO_LARGE, "getContainer", err2.Error())
			return nil
		}
		err = err2
	}
	if err2 = oprot.Flush(); err == nil && err2 != nil {
		if frugal.IsErrTooLarge(err2) {
			gatewaytestWriteApplicationError(ctx, oprot, frugal.APPLICATION_EXCEPTION_RESPONSE_TOO_LARGE, "getContainer", err2.Error())
			return nil
		}
		err = err2
	}
	return err
}

func gatewaytestWriteApplicationError(ctx frugal.FContext, oprot *frugal.FProtocol, type_ int32, method, message string) error {
	x := thrift.NewTApplicationException(type_, message)
	oprot.WriteResponseHeader(ctx)
	oprot.WriteMessageBegin(method, thrift.EXCEPTION, 0)
	x.Write(oprot)
	oprot.WriteMessageEnd()
	oprot.Flush()
	return x
}

type GatewayTestGetContainerArgs struct {
	BaseType *BaseType `thrift:"baseType,1" db:"baseType" json:"baseType"`
}

func NewGatewayTestGetContainerArgs() *GatewayTestGetContainerArgs {
	return &GatewayTestGetContainerArgs{}
}

var GatewayTestGetContainerArgs_BaseType_DEFAULT *BaseType

func (p *GatewayTestGetContainerArgs) IsSetBaseType() bool {
	return p.BaseType != nil
}

func (p *GatewayTestGetContainerArgs) GetBaseType() *BaseType {
	if !p.IsSetBaseType() {
		return GatewayTestGetContainerArgs_BaseType_DEFAULT
	}
	return p.BaseType
}

func (p *GatewayTestGetContainerArgs) Read(iprot thrift.TProtocol) error {
	if _, err := iprot.ReadStructBegin(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T read error: ", p), err)
	}

	for {
		_, fieldTypeId, fieldId, err := iprot.ReadFieldBegin()
		if err != nil {
			return thrift.PrependError(fmt.Sprintf("%T field %d read error: ", p, fieldId), err)
		}
		if fieldTypeId == thrift.STOP {
			break
		}
		switch fieldId {
		case 1:
			if err := p.ReadField1(iprot); err != nil {
				return err
			}
		default:
			if err := iprot.Skip(fieldTypeId); err != nil {
				return err
			}
		}
		if err := iprot.ReadFieldEnd(); err != nil {
			return err
		}
	}
	if err := iprot.ReadStructEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T read struct end error: ", p), err)
	}
	return nil
}

func (p *GatewayTestGetContainerArgs) ReadField1(iprot thrift.TProtocol) error {
	p.BaseType = NewBaseType()
	if err := p.BaseType.Read(iprot); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T error reading struct: ", p.BaseType), err)
	}
	return nil
}

func (p *GatewayTestGetContainerArgs) Write(oprot thrift.TProtocol) error {
	if err := oprot.WriteStructBegin("GetContainer_args"); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write struct begin error: ", p), err)
	}
	if err := p.writeField1(oprot); err != nil {
		return err
	}
	if err := oprot.WriteFieldStop(); err != nil {
		return thrift.PrependError("write field stop error: ", err)
	}
	if err := oprot.WriteStructEnd(); err != nil {
		return thrift.PrependError("write struct stop error: ", err)
	}
	return nil
}

func (p *GatewayTestGetContainerArgs) writeField1(oprot thrift.TProtocol) error {
	if err := oprot.WriteFieldBegin("baseType", thrift.STRUCT, 1); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field begin error 1:baseType: ", p), err)
	}
	if err := p.BaseType.Write(oprot); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T error writing struct: ", p.BaseType), err)
	}
	if err := oprot.WriteFieldEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field end error 1:baseType: ", p), err)
	}
	return nil
}

func (p *GatewayTestGetContainerArgs) String() string {
	if p == nil {
		return "<nil>"
	}
	return fmt.Sprintf("GatewayTestGetContainerArgs(%+v)", *p)
}

type GatewayTestGetContainerResult struct {
	Success *ContainerType `thrift:"success,0" db:"success" json:"success,omitempty"`
}

func NewGatewayTestGetContainerResult() *GatewayTestGetContainerResult {
	return &GatewayTestGetContainerResult{}
}

var GatewayTestGetContainerResult_Success_DEFAULT *ContainerType

func (p *GatewayTestGetContainerResult) IsSetSuccess() bool {
	return p.Success != nil
}

func (p *GatewayTestGetContainerResult) GetSuccess() *ContainerType {
	if !p.IsSetSuccess() {
		return GatewayTestGetContainerResult_Success_DEFAULT
	}
	return p.Success
}

func (p *GatewayTestGetContainerResult) Read(iprot thrift.TProtocol) error {
	if _, err := iprot.ReadStructBegin(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T read error: ", p), err)
	}

	for {
		_, fieldTypeId, fieldId, err := iprot.ReadFieldBegin()
		if err != nil {
			return thrift.PrependError(fmt.Sprintf("%T field %d read error: ", p, fieldId), err)
		}
		if fieldTypeId == thrift.STOP {
			break
		}
		switch fieldId {
		case 0:
			if err := p.ReadField0(iprot); err != nil {
				return err
			}
		default:
			if err := iprot.Skip(fieldTypeId); err != nil {
				return err
			}
		}
		if err := iprot.ReadFieldEnd(); err != nil {
			return err
		}
	}
	if err := iprot.ReadStructEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T read struct end error: ", p), err)
	}
	return nil
}

func (p *GatewayTestGetContainerResult) ReadField0(iprot thrift.TProtocol) error {
	p.Success = NewContainerType()
	if err := p.Success.Read(iprot); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T error reading struct: ", p.Success), err)
	}
	return nil
}

func (p *GatewayTestGetContainerResult) Write(oprot thrift.TProtocol) error {
	if err := oprot.WriteStructBegin("GetContainer_result"); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write struct begin error: ", p), err)
	}
	if err := p.writeField0(oprot); err != nil {
		return err
	}
	if err := oprot.WriteFieldStop(); err != nil {
		return thrift.PrependError("write field stop error: ", err)
	}
	if err := oprot.WriteStructEnd(); err != nil {
		return thrift.PrependError("write struct stop error: ", err)
	}
	return nil
}

func (p *GatewayTestGetContainerResult) writeField0(oprot thrift.TProtocol) error {
	if p.IsSetSuccess() {
		if err := oprot.WriteFieldBegin("success", thrift.STRUCT, 0); err != nil {
			return thrift.PrependError(fmt.Sprintf("%T write field begin error 0:success: ", p), err)
		}
		if err := p.Success.Write(oprot); err != nil {
			return thrift.PrependError(fmt.Sprintf("%T error writing struct: ", p.Success), err)
		}
		if err := oprot.WriteFieldEnd(); err != nil {
			return thrift.PrependError(fmt.Sprintf("%T write field end error 0:success: ", p), err)
		}
	}
	return nil
}

func (p *GatewayTestGetContainerResult) String() string {
	if p == nil {
		return "<nil>"
	}
	return fmt.Sprintf("GatewayTestGetContainerResult(%+v)", *p)
}
