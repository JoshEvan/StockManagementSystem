
1. [webpack.config.js](./webpack.config.js) build tsx files into one bundle.js file (this config.js is to define the output file js name)
2. [index.html](./index.html) will import / include the generated file from [webpack.config.js](./webpack.config.js) --> name must match
3. [server.js](./server.js) is express.js config to serve the index.html file --> nginx like routing
