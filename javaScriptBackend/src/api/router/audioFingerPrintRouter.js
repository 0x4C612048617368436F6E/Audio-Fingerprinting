const express = require("express");
const audioFingerPrintRouter = express.Router();
const audioFingerPrintController = require("../../controller/audioFingerPrintController");
audioFingerPrintRouter.route("/").post(audioFingerPrintController);

module.exports = audioFingerPrintRouter;
