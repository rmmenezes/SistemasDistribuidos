# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: helloworld.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='helloworld.proto',
  package='',
  syntax='proto3',
  serialized_options=None,
  serialized_pb=_b('\n\x10helloworld.proto\"\"\n\tMusicList\x12\x15\n\x05music\x18\x01 \x03(\x0b\x32\x06.Music\"\x15\n\x07MusicId\x12\n\n\x02id\x18\x01 \x01(\x05\"V\n\x05Music\x12\n\n\x02id\x18\x01 \x01(\x05\x12\r\n\x05title\x18\x02 \x01(\t\x12\x0c\n\x04\x62\x61nd\x18\x03 \x01(\t\x12\r\n\x05genre\x18\x04 \x01(\t\x12\x15\n\x05music\x18\x05 \x03(\x0b\x32\x06.Music\"\x1d\n\x0c\x42ooleanReply\x12\r\n\x05reply\x18\x01 \x01(\x08\"!\n\x0bListRequest\x12\x12\n\nlistMusics\x18\x01 \x01(\x08\x32\x85\x01\n\tMusicServ\x12)\n\rgetListMusics\x12\x0c.ListRequest\x1a\x06.Music\"\x00\x30\x01\x12#\n\x08\x61\x64\x64Music\x12\x06.Music\x1a\r.BooleanReply\"\x00\x12(\n\x0b\x64\x65leteMusic\x12\x08.MusicId\x1a\r.BooleanReply\"\x00\x62\x06proto3')
)




_MUSICLIST = _descriptor.Descriptor(
  name='MusicList',
  full_name='MusicList',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='music', full_name='MusicList.music', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=20,
  serialized_end=54,
)


_MUSICID = _descriptor.Descriptor(
  name='MusicId',
  full_name='MusicId',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='MusicId.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=56,
  serialized_end=77,
)


_MUSIC = _descriptor.Descriptor(
  name='Music',
  full_name='Music',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='Music.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='title', full_name='Music.title', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='band', full_name='Music.band', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='genre', full_name='Music.genre', index=3,
      number=4, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='music', full_name='Music.music', index=4,
      number=5, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=79,
  serialized_end=165,
)


_BOOLEANREPLY = _descriptor.Descriptor(
  name='BooleanReply',
  full_name='BooleanReply',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='reply', full_name='BooleanReply.reply', index=0,
      number=1, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=167,
  serialized_end=196,
)


_LISTREQUEST = _descriptor.Descriptor(
  name='ListRequest',
  full_name='ListRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='listMusics', full_name='ListRequest.listMusics', index=0,
      number=1, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=198,
  serialized_end=231,
)

_MUSICLIST.fields_by_name['music'].message_type = _MUSIC
_MUSIC.fields_by_name['music'].message_type = _MUSIC
DESCRIPTOR.message_types_by_name['MusicList'] = _MUSICLIST
DESCRIPTOR.message_types_by_name['MusicId'] = _MUSICID
DESCRIPTOR.message_types_by_name['Music'] = _MUSIC
DESCRIPTOR.message_types_by_name['BooleanReply'] = _BOOLEANREPLY
DESCRIPTOR.message_types_by_name['ListRequest'] = _LISTREQUEST
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

MusicList = _reflection.GeneratedProtocolMessageType('MusicList', (_message.Message,), dict(
  DESCRIPTOR = _MUSICLIST,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:MusicList)
  ))
_sym_db.RegisterMessage(MusicList)

MusicId = _reflection.GeneratedProtocolMessageType('MusicId', (_message.Message,), dict(
  DESCRIPTOR = _MUSICID,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:MusicId)
  ))
_sym_db.RegisterMessage(MusicId)

Music = _reflection.GeneratedProtocolMessageType('Music', (_message.Message,), dict(
  DESCRIPTOR = _MUSIC,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:Music)
  ))
_sym_db.RegisterMessage(Music)

BooleanReply = _reflection.GeneratedProtocolMessageType('BooleanReply', (_message.Message,), dict(
  DESCRIPTOR = _BOOLEANREPLY,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:BooleanReply)
  ))
_sym_db.RegisterMessage(BooleanReply)

ListRequest = _reflection.GeneratedProtocolMessageType('ListRequest', (_message.Message,), dict(
  DESCRIPTOR = _LISTREQUEST,
  __module__ = 'helloworld_pb2'
  # @@protoc_insertion_point(class_scope:ListRequest)
  ))
_sym_db.RegisterMessage(ListRequest)



_MUSICSERV = _descriptor.ServiceDescriptor(
  name='MusicServ',
  full_name='MusicServ',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  serialized_start=234,
  serialized_end=367,
  methods=[
  _descriptor.MethodDescriptor(
    name='getListMusics',
    full_name='MusicServ.getListMusics',
    index=0,
    containing_service=None,
    input_type=_LISTREQUEST,
    output_type=_MUSIC,
    serialized_options=None,
  ),
  _descriptor.MethodDescriptor(
    name='addMusic',
    full_name='MusicServ.addMusic',
    index=1,
    containing_service=None,
    input_type=_MUSIC,
    output_type=_BOOLEANREPLY,
    serialized_options=None,
  ),
  _descriptor.MethodDescriptor(
    name='deleteMusic',
    full_name='MusicServ.deleteMusic',
    index=2,
    containing_service=None,
    input_type=_MUSICID,
    output_type=_BOOLEANREPLY,
    serialized_options=None,
  ),
])
_sym_db.RegisterServiceDescriptor(_MUSICSERV)

DESCRIPTOR.services_by_name['MusicServ'] = _MUSICSERV

# @@protoc_insertion_point(module_scope)
