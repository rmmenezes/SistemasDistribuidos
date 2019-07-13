# Copyright 2015, Google Inc.
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are
# met:
#
#     * Redistributions of source code must retain the above copyright
# notice, this list of conditions and the following disclaimer.
#     * Redistributions in binary form must reproduce the above
# copyright notice, this list of conditions and the following disclaimer
# in the documentation and/or other materials provided with the
# distribution.
#     * Neither the name of Google Inc. nor the names of its
# contributors may be used to endorse or promote products derived from
# this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
# "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
# LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
# A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
# OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
# SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
# LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
# DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
# THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
# OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

"""The Python implementation of the GRPC helloworld.Greeter client."""

from __future__ import print_function
import grpc
import helloworld_pb2
import helloworld_pb2_grpc

def run():
  channel = grpc.insecure_channel("127.0.0.1:9090") #encontra o servidor
  stb = helloworld_pb2_grpc.MusicServStub(channel)  #abistrai as funçoes do servidor no stb

  while(True):
    print("1 - Adicionar musica \n2 - Deletar Musica \n3 - Listar Musicas \n4 - Sair")
    opcao = input("Indique a ação que deseja:")

    if opcao == "1":
      musica = helloworld_pb2.Music()
      musica.id = int(input("Id: "))
      nomeMusica = input("Titulo da Musica: ")
      musica.title = nomeMusica
      musica.band = input("Banda: ")
      musica.genre = input("Genero: ")
      response = stb.addMusic(musica)
      if response.reply:
        print("Musica ["+ str(nomeMusica) +"] Inserida com Sucesso")
    
    elif opcao == "2":
      idMusica = int(input("Informe o Id da musica referente: "))
      response = stb.deleteMusic(helloworld_pb2.MusicId(id = idMusica))
      if response.reply:
        print("Musica de Id ["+ str(idMusica) +"] Deletada com Sucesso")
    
    elif opcao == "3":
      music_list = stb.getListMusics(helloworld_pb2.ListRequest(listMusics = True))
      for music in music_list:
        print(music)
      
    elif opcao == "4":
      print("bye")
      exit(0)

if __name__ == "__main__":
  run() #executa a def run
