// Autogenerated by Frugal Compiler (2.27.1)
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

import 'dart:typed_data' show Uint8List;
import 'package:thrift/thrift.dart' as thrift;
import 'package:variety/variety.dart' as t_variety;
import 'package:actual_base_dart/actual_base_dart.dart' as t_actual_base_dart;
import 'package:intermediate_include/intermediate_include.dart' as t_intermediate_include;
import 'package:validStructs/validStructs.dart' as t_validStructs;
import 'package:ValidTypes/ValidTypes.dart' as t_ValidTypes;
import 'package:subdir_include_ns/subdir_include_ns.dart' as t_subdir_include_ns;

/// This docstring gets added to the generated code because it has
/// the @ sign.
class Event implements thrift.TBase {
  static final thrift.TStruct _STRUCT_DESC = new thrift.TStruct("Event");
  static final thrift.TField _ID_FIELD_DESC = new thrift.TField("ID", thrift.TType.I64, 1);
  static final thrift.TField _MESSAGE_FIELD_DESC = new thrift.TField("Message", thrift.TType.STRING, 2);

  /// ID is a unique identifier for an event.
  int iD = 0;
  static const int ID = 1;
  /// Message contains the event payload.
  String message;
  static const int MESSAGE = 2;


  Event() {
    this.iD = t_variety.VarietyConstants.DEFAULT_ID;
  }

  @deprecated
  bool isSetID() => iD != null && iD != t_variety.VarietyConstants.DEFAULT_ID;

  @deprecated
  unsetID() => iD = null;

  @deprecated
  bool isSetMessage() => message != null;

  @deprecated
  unsetMessage() => message = null;

  @override
  getFieldValue(int fieldID) {
    switch (fieldID) {
      case ID:
        return this.iD;
      case MESSAGE:
        return this.message;
      default:
        throw new ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  @override
  setFieldValue(int fieldID, Object value) {
    switch (fieldID) {
      case ID:
        iD = value as int; // ignore: avoid_as
        break;

      case MESSAGE:
        message = value as String; // ignore: avoid_as
        break;

      default:
        throw new ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  // Returns true if the field corresponding to fieldID is set (has been assigned a value) and false otherwise
  @override
  bool isSet(int fieldID) {
    switch (fieldID) {
      case ID:
        return iD != null && iD != t_variety.VarietyConstants.DEFAULT_ID;

      case MESSAGE:
        return message != null;

      default:
        throw new ArgumentError("Field $fieldID doesn't exist!");
    }
  }

  @override
  read(thrift.TProtocol iprot) {
    iprot.readStructBegin();
    for (thrift.TField field = iprot.readFieldBegin();
        field.type != thrift.TType.STOP;
        field = iprot.readFieldBegin()) {
      switch (field.id) {
        case ID:
          if (field.type == thrift.TType.I64) {
            iD = iprot.readI64();
          } else {
            thrift.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case MESSAGE:
          if (field.type == thrift.TType.STRING) {
            message = iprot.readString();
          } else {
            thrift.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          thrift.TProtocolUtil.skip(iprot, field.type);
          break;
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    validate();
  }

  @override
  write(thrift.TProtocol oprot) {
    validate();

    oprot.writeStructBegin(_STRUCT_DESC);
    oprot.writeFieldBegin(_ID_FIELD_DESC);
    oprot.writeI64(iD);
    oprot.writeFieldEnd();
    if (message != null) {
      oprot.writeFieldBegin(_MESSAGE_FIELD_DESC);
      oprot.writeString(message);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @override
  String toString() {
    StringBuffer ret = new StringBuffer("Event(");

    ret.write("iD:");
    ret.write(this.iD);

    ret.write(", ");
    ret.write("message:");
    if (this.message == null) {
      ret.write("null");
    } else {
      ret.write(this.message);
    }

    ret.write(")");

    return ret.toString();
  }

  @override
  bool operator ==(Object o) {
    if (o is Event) {
      return this.iD == o.iD &&
        this.message == o.message;
    }
    return false;
  }

  @override
  int get hashCode {
    var value = 17;
    value = (value * 31) ^ iD.hashCode;
    value = (value * 31) ^ message.hashCode;
    return value;
  }

  Event clone({
    int iD: null,
    String message: null,
  }) {
    return new Event()
      ..iD = iD ?? this.iD
      ..message = message ?? this.message;
  }

  validate() {
  }
}
