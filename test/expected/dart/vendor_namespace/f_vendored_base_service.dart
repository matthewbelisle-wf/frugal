// Autogenerated by Frugal Compiler (3.9.4)
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING



// ignore_for_file: unused_import
// ignore_for_file: unused_field
import 'dart:async';
import 'dart:typed_data' show Uint8List;

import 'package:collection/collection.dart';
import 'package:logging/logging.dart' as logging;
import 'package:thrift/thrift.dart' as thrift;
import 'package:frugal/frugal.dart' as frugal;
import 'package:w_common/disposable.dart' as disposable;

import 'package:vendor_namespace/vendor_namespace.dart' as t_vendor_namespace;


abstract class FVendoredBase {}

FVendoredBaseClient fVendoredBaseClientFactory(frugal.FServiceProvider provider, {List<frugal.Middleware> middleware}) =>
    FVendoredBaseClient(provider, middleware);

class FVendoredBaseClient extends disposable.Disposable implements FVendoredBase {
  static final logging.Logger _frugalLog = logging.Logger('VendoredBase');
  Map<String, frugal.FMethod> _methods;

  FVendoredBaseClient(frugal.FServiceProvider provider, [List<frugal.Middleware> middleware])
      : this._provider = provider {
    _transport = provider.transport;
    _protocolFactory = provider.protocolFactory;
    var combined = middleware ?? [];
    combined.addAll(provider.middleware);
    this._methods = {};
  }

  frugal.FServiceProvider _provider;
  frugal.FTransport _transport;
  frugal.FProtocolFactory _protocolFactory;

  @override
  Future<Null> onDispose() async {
    if (_provider is disposable.Disposable && !_provider.isOrWillBeDisposed)  {
      return _provider.dispose();
    }
    return null;
  }

}

