FROM node:16-alpine

RUN npm config set registry http://registry.npmjs.org/
RUN npm config set strict-ssl false
WORKDIR /tmp
COPY package.json /tmp/
RUN npm install --force

WORKDIR /usr/src/app
COPY . /usr/src/app/
RUN cp -a /tmp/node_modules /usr/src/app/

ENV PORT=5001
EXPOSE 5001

RUN npm run build-development
CMD [ "sh","/usr/src/app/start.sh" ]
