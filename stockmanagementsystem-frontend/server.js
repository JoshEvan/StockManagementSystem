const express = require('express');
const path = require('path');
const port = process.env.PORT || 5001;
const app = express();

// require('dotenv').config(); // not working

api_url = process.env.NODE_ENV
const _env = app.get('env');

// the __dirname is the current directory from where the script is running
app.use(express.static(__dirname));

// send the user to index html page inspite of the url
app.get('*', (req, res) => {
  res.sendFile(path.resolve(__dirname, 'index.html'));
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}. ${api_url} ${_env}`)
});