const express = require("express");
const app = express();
const PORT = process.env.PORT || 5000;

//routes

app.listen(PORT, () => {
  console.log(`Server is listening on port ${PORT}`);
});
