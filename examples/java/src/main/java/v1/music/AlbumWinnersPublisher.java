/**
 * Autogenerated by Frugal Compiler (3.9.9)
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */

package v1.music;

import com.workiva.frugal.FContext;
import com.workiva.frugal.exception.TApplicationExceptionType;
import com.workiva.frugal.middleware.InvocationHandler;
import com.workiva.frugal.middleware.ServiceMiddleware;
import com.workiva.frugal.protocol.*;
import com.workiva.frugal.provider.FScopeProvider;
import com.workiva.frugal.transport.FPublisherTransport;
import com.workiva.frugal.transport.FSubscriberTransport;
import com.workiva.frugal.transport.FSubscription;
import com.workiva.frugal.transport.TMemoryOutputBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.protocol.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Generated;




@Generated(value = "Autogenerated by Frugal Compiler (3.9.9)")
public class AlbumWinnersPublisher {

	/**
	 * Scopes are a Frugal extension to the IDL for declaring PubSub
	 * semantics. Subscribers to this scope will be notified if they win a contest.
	 * Scopes must have a prefix.
	 */
	public interface Iface {
		public void open() throws TException;

		public void close() throws TException;

		public void publishContestStart(FContext ctx, java.util.List<Album> req) throws TException;

		public void publishTimeLeft(FContext ctx, double req) throws TException;

		public void publishWinner(FContext ctx, Album req) throws TException;

	}

	/**
	 * Scopes are a Frugal extension to the IDL for declaring PubSub
	 * semantics. Subscribers to this scope will be notified if they win a contest.
	 * Scopes must have a prefix.
	 */
	public static class Client implements Iface {
		private static final String DELIMITER = ".";

		private final Iface target;
		private final Iface proxy;

		public Client(FScopeProvider provider, ServiceMiddleware... middleware) {
			target = new InternalAlbumWinnersPublisher(provider);
			List<ServiceMiddleware> combined = new ArrayList<ServiceMiddleware>(Arrays.asList(middleware));
			combined.addAll(provider.getMiddleware());
			middleware = combined.toArray(new ServiceMiddleware[0]);
			proxy = InvocationHandler.composeMiddleware(target, Iface.class, middleware);
		}

		public void open() throws TException {
			target.open();
		}

		public void close() throws TException {
			target.close();
		}

		public void publishContestStart(FContext ctx, java.util.List<Album> req) throws TException {
			proxy.publishContestStart(ctx, req);
		}

		public void publishTimeLeft(FContext ctx, double req) throws TException {
			proxy.publishTimeLeft(ctx, req);
		}

		public void publishWinner(FContext ctx, Album req) throws TException {
			proxy.publishWinner(ctx, req);
		}

		protected static class InternalAlbumWinnersPublisher implements Iface {

			private FScopeProvider provider;
			private FPublisherTransport transport;
			private FProtocolFactory protocolFactory;

			protected InternalAlbumWinnersPublisher() {
			}

			public InternalAlbumWinnersPublisher(FScopeProvider provider) {
				this.provider = provider;
			}

			public void open() throws TException {
				FScopeProvider.Publisher publisher = provider.buildPublisher();
				transport = publisher.getTransport();
				protocolFactory = publisher.getProtocolFactory();
				transport.open();
			}

			public void close() throws TException {
				transport.close();
			}

			public void publishContestStart(FContext ctx, java.util.List<Album> req) throws TException {
				String op = "ContestStart";
				String prefix = "v1.music.";
				String topic = String.format("%sAlbumWinners%s%s", prefix, DELIMITER, op);
				TMemoryOutputBuffer memoryBuffer = new TMemoryOutputBuffer(transport.getPublishSizeLimit());
				FProtocol oprot = protocolFactory.getProtocol(memoryBuffer);
				oprot.writeRequestHeader(ctx);
				oprot.writeMessageBegin(new TMessage(op, TMessageType.CALL, 0));
				oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, req.size()));
				for (Album elem21 : req) {
					elem21.write(oprot);
				}
				oprot.writeListEnd();
				oprot.writeMessageEnd();
				transport.publish(topic, memoryBuffer.getWriteBytes());
			}


			public void publishTimeLeft(FContext ctx, double req) throws TException {
				String op = "TimeLeft";
				String prefix = "v1.music.";
				String topic = String.format("%sAlbumWinners%s%s", prefix, DELIMITER, op);
				TMemoryOutputBuffer memoryBuffer = new TMemoryOutputBuffer(transport.getPublishSizeLimit());
				FProtocol oprot = protocolFactory.getProtocol(memoryBuffer);
				oprot.writeRequestHeader(ctx);
				oprot.writeMessageBegin(new TMessage(op, TMessageType.CALL, 0));
				double elem22 = req;
				oprot.writeDouble(elem22);
				oprot.writeMessageEnd();
				transport.publish(topic, memoryBuffer.getWriteBytes());
			}


			public void publishWinner(FContext ctx, Album req) throws TException {
				String op = "Winner";
				String prefix = "v1.music.";
				String topic = String.format("%sAlbumWinners%s%s", prefix, DELIMITER, op);
				TMemoryOutputBuffer memoryBuffer = new TMemoryOutputBuffer(transport.getPublishSizeLimit());
				FProtocol oprot = protocolFactory.getProtocol(memoryBuffer);
				oprot.writeRequestHeader(ctx);
				oprot.writeMessageBegin(new TMessage(op, TMessageType.CALL, 0));
				req.write(oprot);
				oprot.writeMessageEnd();
				transport.publish(topic, memoryBuffer.getWriteBytes());
			}
		}
	}
}
