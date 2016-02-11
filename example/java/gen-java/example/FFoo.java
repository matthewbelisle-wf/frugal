/**
 * Autogenerated by Frugal Compiler (1.0.0-RC2)
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */

package example;

import com.workiva.frugal.*;
import com.workiva.frugal.exception.FTimeoutException;
import com.workiva.frugal.processor.FBaseProcessor;
import com.workiva.frugal.processor.FProcessor;
import com.workiva.frugal.processor.FProcessorFunction;
import com.workiva.frugal.registry.FAsyncCallback;
import com.workiva.frugal.registry.FClientRegistry;
import com.workiva.frugal.transport.FTransport;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.transport.TTransport;

import javax.annotation.Generated;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;


@Generated(value = "Autogenerated by Frugal Compiler (1.0.0-RC2)", date = "2016-2-11")
public class FFoo {

	/**
	 * This is a thrift service. Frugal will generate bindings that include 
	 * a frugal Context for each service call.
	 */
	public interface Iface extends base.FBaseFoo.Iface {

		/**
		 * Ping the server.
		 */
		public void ping(FContext ctx) throws TException;

		/**
		 * Blah the server.
		 */
		public long blah(FContext ctx, int num, String Str, Event event) throws TException, AwesomeException, base.api_exception;

		/**
		 * oneway methods don't receive a response from the server.
		 */
		public void oneWay(FContext ctx, long id, java.util.Map<Integer, String> req) throws TException;

}

	public static class Client extends base.FBaseFoo.Client implements Iface {

		private static final Object WRITE_LOCK = new Object();

		private FTransport transport;
		private FProtocolFactory protocolFactory;
		private FProtocol inputProtocol;
		private FProtocol outputProtocol;

		public Client(FTransport transport, FProtocolFactory protocolFactory) {
			super(transport, protocolFactory);
			this.transport = transport;
			this.transport.setRegistry(new FClientRegistry());
			this.protocolFactory = protocolFactory;
			this.inputProtocol = this.protocolFactory.getProtocol(this.transport);
			this.outputProtocol = this.protocolFactory.getProtocol(this.transport);
		}

		/**
		 * Ping the server.
		 */
		public void ping(FContext ctx) throws TException {
			FProtocol oprot = this.outputProtocol;
			BlockingQueue<Object> result = new ArrayBlockingQueue<>(1);
			this.transport.register(ctx, recvPingHandler(ctx, result));
			try {
				synchronized (WRITE_LOCK) {
					oprot.writeRequestHeader(ctx);
					oprot.writeMessageBegin(new TMessage("ping", TMessageType.CALL, 0));
					Foo.ping_args args = new Foo.ping_args();
					args.write(oprot);
					oprot.writeMessageEnd();
					oprot.getTransport().flush();
				}

				Object res = null;
				try {
					res = result.poll(ctx.getTimeout(), TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "ping interrupted: " + e.getMessage());
				}
				if (res == null) {
					throw new FTimeoutException("ping timed out");
				}
				if (res instanceof TException) {
					throw (TException) res;
				}
				Foo.ping_result r = (Foo.ping_result) res;
			} finally {
				this.transport.unregister(ctx);
			}
		}

		private FAsyncCallback recvPingHandler(final FContext ctx, final BlockingQueue<Object> result) {
			return new FAsyncCallback() {
				public void onMessage(TTransport tr) throws TException {
					FProtocol iprot = Client.this.protocolFactory.getProtocol(tr);
					try {
						iprot.readResponseHeader(ctx);
						TMessage message = iprot.readMessageBegin();
						if (!message.name.equals("ping")) {
							throw new TApplicationException(TApplicationException.WRONG_METHOD_NAME, "ping failed: wrong method name");
						}
						if (message.type == TMessageType.EXCEPTION) {
							TApplicationException e = TApplicationException.read(iprot);
							iprot.readMessageEnd();
							throw e;
						}
						if (message.type != TMessageType.REPLY) {
							throw new TApplicationException(TApplicationException.INVALID_MESSAGE_TYPE, "ping failed: invalid message type");
						}
						Foo.ping_result res = new Foo.ping_result();
						res.read(iprot);
						iprot.readMessageEnd();
						try {
							result.put(res);
						} catch (InterruptedException e) {
							throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "ping interrupted: " + e.getMessage());
						}
					} catch (TException e) {
						try {
							result.put(e);
						} finally {
							throw e;
						}
					}
				}
			};
		}

		/**
		 * Blah the server.
		 */
		public long blah(FContext ctx, int num, String Str, Event event) throws TException, AwesomeException, base.api_exception {
			FProtocol oprot = this.outputProtocol;
			BlockingQueue<Object> result = new ArrayBlockingQueue<>(1);
			this.transport.register(ctx, recvBlahHandler(ctx, result));
			try {
				synchronized (WRITE_LOCK) {
					oprot.writeRequestHeader(ctx);
					oprot.writeMessageBegin(new TMessage("blah", TMessageType.CALL, 0));
					Foo.blah_args args = new Foo.blah_args();
					args.setNum(num);
					args.setStr(Str);
					args.setEvent(event);
					args.write(oprot);
					oprot.writeMessageEnd();
					oprot.getTransport().flush();
				}

				Object res = null;
				try {
					res = result.poll(ctx.getTimeout(), TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "blah interrupted: " + e.getMessage());
				}
				if (res == null) {
					throw new FTimeoutException("blah timed out");
				}
				if (res instanceof TException) {
					throw (TException) res;
				}
				Foo.blah_result r = (Foo.blah_result) res;
				if (r.isSetSuccess()) {
					return r.success;
				}
				if (r.awe != null) {
					throw r.awe;
				}
				if (r.api != null) {
					throw r.api;
				}
				throw new TApplicationException(TApplicationException.MISSING_RESULT, "blah failed: unknown result");
			} finally {
				this.transport.unregister(ctx);
			}
		}

		private FAsyncCallback recvBlahHandler(final FContext ctx, final BlockingQueue<Object> result) {
			return new FAsyncCallback() {
				public void onMessage(TTransport tr) throws TException {
					FProtocol iprot = Client.this.protocolFactory.getProtocol(tr);
					try {
						iprot.readResponseHeader(ctx);
						TMessage message = iprot.readMessageBegin();
						if (!message.name.equals("blah")) {
							throw new TApplicationException(TApplicationException.WRONG_METHOD_NAME, "blah failed: wrong method name");
						}
						if (message.type == TMessageType.EXCEPTION) {
							TApplicationException e = TApplicationException.read(iprot);
							iprot.readMessageEnd();
							throw e;
						}
						if (message.type != TMessageType.REPLY) {
							throw new TApplicationException(TApplicationException.INVALID_MESSAGE_TYPE, "blah failed: invalid message type");
						}
						Foo.blah_result res = new Foo.blah_result();
						res.read(iprot);
						iprot.readMessageEnd();
						try {
							result.put(res);
						} catch (InterruptedException e) {
							throw new TApplicationException(TApplicationException.INTERNAL_ERROR, "blah interrupted: " + e.getMessage());
						}
					} catch (TException e) {
						try {
							result.put(e);
						} finally {
							throw e;
						}
					}
				}
			};
		}

		/**
		 * oneway methods don't receive a response from the server.
		 */
		public void oneWay(FContext ctx, long id, java.util.Map<Integer, String> req) throws TException {
			FProtocol oprot = this.outputProtocol;
			synchronized (WRITE_LOCK) {
				oprot.writeRequestHeader(ctx);
				oprot.writeMessageBegin(new TMessage("oneWay", TMessageType.ONEWAY, 0));
				Foo.oneWay_args args = new Foo.oneWay_args();
				args.setId(id);
				args.setReq(req);
				args.write(oprot);
				oprot.writeMessageEnd();
				oprot.getTransport().flush();
			}
		}
	}

	public static class Processor extends base.FBaseFoo.Processor implements FProcessor {

	public Processor(Iface iface) {
		super(iface, getProcessMap(iface, new java.util.HashMap<String, FProcessorFunction>()));
	}

	protected Processor(Iface iface, java.util.Map<String, FProcessorFunction> processMap) {
		super(iface, getProcessMap(iface, processMap));
	}

		private static java.util.Map<String, FProcessorFunction> getProcessMap(Iface handler, java.util.Map<String, FProcessorFunction> processMap) {
			processMap.put("ping", new Ping(handler));
			processMap.put("blah", new Blah(handler));
			processMap.put("oneWay", new OneWay(handler));
			return processMap;
		}

		private static class Ping implements FProcessorFunction {

			private Iface handler;

			public Ping(Iface handler) {
				this.handler = handler;
			}

			public void process(FContext ctx, FProtocol iprot, FProtocol oprot) throws TException {
				Foo.ping_args args = new Foo.ping_args();
				try {
					args.read(iprot);
				} catch (TException e) {
					iprot.readMessageEnd();
					TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
					synchronized (WRITE_LOCK) {
						oprot.writeResponseHeader(ctx);
						oprot.writeMessageBegin(new TMessage("ping", TMessageType.EXCEPTION, 0));
						x.write(oprot);
						oprot.writeMessageEnd();
						oprot.getTransport().flush();
					}
					throw e;
				}

				iprot.readMessageEnd();
				Foo.ping_result result = new Foo.ping_result();
				try {
					this.handler.ping(ctx);
				} catch (TException e) {
					TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing ping: " + e.getMessage());
					synchronized (WRITE_LOCK) {
						oprot.writeResponseHeader(ctx);
						oprot.writeMessageBegin(new TMessage("ping", TMessageType.EXCEPTION, 0));
						x.write(oprot);
						oprot.writeMessageEnd();
						oprot.getTransport().flush();
					}
					throw e;
				}
				synchronized (WRITE_LOCK) {
					oprot.writeResponseHeader(ctx);
					oprot.writeMessageBegin(new TMessage("ping", TMessageType.REPLY, 0));
					result.write(oprot);
					oprot.writeMessageEnd();
					oprot.getTransport().flush();
				}
			}
		}

		private static class Blah implements FProcessorFunction {

			private Iface handler;

			public Blah(Iface handler) {
				this.handler = handler;
			}

			public void process(FContext ctx, FProtocol iprot, FProtocol oprot) throws TException {
				Foo.blah_args args = new Foo.blah_args();
				try {
					args.read(iprot);
				} catch (TException e) {
					iprot.readMessageEnd();
					TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
					synchronized (WRITE_LOCK) {
						oprot.writeResponseHeader(ctx);
						oprot.writeMessageBegin(new TMessage("blah", TMessageType.EXCEPTION, 0));
						x.write(oprot);
						oprot.writeMessageEnd();
						oprot.getTransport().flush();
					}
					throw e;
				}

				iprot.readMessageEnd();
				Foo.blah_result result = new Foo.blah_result();
				try {
					result.success = this.handler.blah(ctx, args.num, args.Str, args.event);
					result.setSuccessIsSet(true);
				} catch (AwesomeException awe) {
					result.awe = awe;
				} catch (base.api_exception api) {
					result.api = api;
				} catch (TException e) {
					TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing blah: " + e.getMessage());
					synchronized (WRITE_LOCK) {
						oprot.writeResponseHeader(ctx);
						oprot.writeMessageBegin(new TMessage("blah", TMessageType.EXCEPTION, 0));
						x.write(oprot);
						oprot.writeMessageEnd();
						oprot.getTransport().flush();
					}
					throw e;
				}
				synchronized (WRITE_LOCK) {
					oprot.writeResponseHeader(ctx);
					oprot.writeMessageBegin(new TMessage("blah", TMessageType.REPLY, 0));
					result.write(oprot);
					oprot.writeMessageEnd();
					oprot.getTransport().flush();
				}
			}
		}

		private static class OneWay implements FProcessorFunction {

			private Iface handler;

			public OneWay(Iface handler) {
				this.handler = handler;
			}

			public void process(FContext ctx, FProtocol iprot, FProtocol oprot) throws TException {
				Foo.oneWay_args args = new Foo.oneWay_args();
				try {
					args.read(iprot);
				} catch (TException e) {
					iprot.readMessageEnd();
					throw e;
				}

				iprot.readMessageEnd();
				this.handler.oneWay(ctx, args.id, args.req);
			}
		}

	}

}