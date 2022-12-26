FROM node:10-alpine

RUN npm install webpack -g

WORKDIR /tmp
COPY package.json /tmp/
RUN npm config set registry http://registry.npmjs.org/ && npm install

WORKDIR /usr/src/app
COPY . /usr/src/app/
RUN cp -a /tmp/node_modules /usr/src/app/

ENV PORT=5001
EXPOSE 5001

CMD [ "sh","/usr/src/app/start.sh" ]
