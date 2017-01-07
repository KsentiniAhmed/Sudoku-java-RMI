# Sudoku-java-RMI
jeu sudoku avec java (RMI) : j'ai utilisé la notion de callback, la notion dynamique coté client et serveur, la notion de sécurité, la notion de thread et la notion de serveur distant  .
#30/12/2016
#java version "1.7.0_91"
  OpenJDK Runtime Environment (IcedTea 2.6.3) (7u91-2.6.3-1)
   OpenJDK 64-Bit Server VM (build 24.91-b01, mixed mode)


#compilation du projet
root@ahmed-PC:~/Bureau/Sudoku/client# javac ICallback.java 
root@ahmed-PC:~/Bureau/Sudoku/client# javac Callback.java
root@ahmed-PC:~/Bureau/Sudoku/client# rmic -v1.1 Callback


root@ahmed-PC:~/Bureau/Sudoku/server# cp ../client/ICallback.class .
root@ahmed-PC:~/Bureau/Sudoku/server# cp ../client/Callback_stub.class .
root@ahmed-PC:~/Bureau/Sudoku/server# javac Servant.java
root@ahmed-PC:~/Bureau/Sudoku/server# javac SudokuInterface.java
root@ahmed-PC:~/Bureau/Sudoku/server# javac Sudoku.java
root@ahmed-PC:~/Bureau/Sudoku/server# javac FabSudokuInterface.java
root@ahmed-PC:~/Bureau/Sudoku/server# javac FabSudokuImpl.java
root@ahmed-PC:~/Bureau/Sudoku/server# rmic -v1.1 Sudoku
root@ahmed-PC:~/Bureau/Sudoku/server# rmic -v1.1 FabSudokuImpl
root@ahmed-PC:~/Bureau/Sudoku/server# javac *.java



root@ahmed-PC:~/Bureau/Sudoku/client# cp ../server/SudokuInterface.class .
root@ahmed-PC:~/Bureau/Sudoku/client# cp ../server/Sudoku_Stub.class .
root@ahmed-PC:~/Bureau/Sudoku/client# cp ../server/FabSudokuInterface.class .
root@ahmed-PC:~/Bureau/Sudoku/client# cp ../server/FabSudokuImpl_Stub.class .
root@ahmed-PC:~/Bureau/Sudoku/client# javac *.java

#on Déplace manuellement ou en utilisant la commande « mv » tous les fichiers du dossier « server » au dossier «www » sauf les fichiers suivants : DynamicSudokuServer.java, DynamicSudokuServer.class, Server.policy

# Déplacer manuellement ou en utilisant la commande « mv » tous les fichiers du dossier « client » au dossier «www » sauf les fichiers suivants : DynamicClient.java, DynamicClient.class, Client.policy, Client2.policy

#si on modifie le code il faut faire la compliation :(www)
root@ahmed-PC:~/Bureau/Sudoku/www# javac *.java 
root@ahmed-PC:~/Bureau/Sudoku/www# rmic -v1.1 Callback 
root@ahmed-PC:~/Bureau/Sudoku/www# rmic -v1.1 Sudoku
root@ahmed-PC:~/Bureau/Sudoku/www# rmic -v1.1 FabSudokuImpl

///compilation coté serveur /////
root@ahmed-PC:~/Bureau/Sudoku/server# javac *.java
///////
///compilation coté client /////
root@ahmed-PC:~/Bureau/Sudoku/server# javac *.java
///////

#execution du projet

root@ahmed-PC:~/Bureau/Sudoku/server# java -Djava.security.policy=server.policy -Djava.rmi.server.codebase=file:///root/Bureau/Sudoku/www/ DynamicSudokuServer 

root@ahmed-PC:~/Bureau/Sudoku/client# java -Djava.security.policy=client.policy -Djava.rmi.server.codebase=file:///root/Bureau/Sudoku/www/ DynamicClient

