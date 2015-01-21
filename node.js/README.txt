#1 Install node.js
----------------------------------
sudo apt-get update
sudo apt-get install nodejs
----------------------------------
#2 Install Node.JS packet manager
----------------------------------
sudo apt-get install npm
----------------------------------
#3 Install library used by server
----------------------------------
Delete the socket.io folder in your node_modules folder. (if exists)
npm install socket.io@0.9.16
----------------------------------
#4 Run rateServer.js
----------------------------------