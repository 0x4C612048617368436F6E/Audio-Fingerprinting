const express = require("express");
const addSongToDatabaseRouter = express.Router();
const addSongToDatabaseController = require("../../controller/addSongToDatabaseController");
addSongToDatabaseRouter.route("/").post(addSongToDatabaseController);

module.exports = addSongToDatabaseRouter;
