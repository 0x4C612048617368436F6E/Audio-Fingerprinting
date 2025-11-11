const express = require("express");
const cors = require("cors");
//load env file
const { loadEnvFile } = require("node:process");
loadEnvFile("./.development.env");
const corOptionsObject = require("./config/corsConfig");
const app = express();
const PORT = process.env.PORT || 5000;
const addSongToDatabaseRouter = require("./api/router/addSongToDatabaseRouter");
const audioFingerPrintRouter = require("./api/router/audioFingerPrintRouter");

//configure form submit middleware
app.use(express.urlencoded({ extended: true }));

//set up cors
app.use(cors(corOptionsObject));

//configure json middleware
app.use(express.json());
//routes
app.use("/api/addSongToDatabase", addSongToDatabaseRouter);
app.use("/api/computeFingerPint", audioFingerPrintRouter);

app.listen(PORT, () => {
  console.log(`Server is listening on port ${PORT}`);
});
