const axios = require("axios");

const addSongToDatabaseController = (req, res, next) => {
  //work with database. For ease of use, only provide Youtube links
  const regexStringOnlyVideoId = /\?v=/;
  const regexStringTrackingSI = /\?si=/;
  const youtubeLink = res.body?.link;
  const indexOfMatchOnlyVideoId = youtubeLink.search(regexStringOnlyVideoId);
  const indexOfMatchTrackingSI = youtubeLink.search(regexStringTrackingSI);
  if (indexOfMatchOnlyVideoId == -1) {
    //if -1, maybe it could be Si
    if (indexOfMatchTrackingSI != -1) {
      //maybe could be si
      const videoIDIndex = "https://youtu.be/".lastIndexOf("/");
      youtubeLink = `https://www.youtube.com/watch?v=${youtubeLink.slice(
        videoIDIndex,
        indexOfMatchTrackingSI
      )}`;
    } else {
      //return unrecognised URL
      return res.status(400).json({
        status: 400,
        message: "Provided URL is correct",
      });
    }
  }
  //We have the correct ULR Plus its ID
  //fecth YouTube detail based of URL.
  /**
   * Need to ensure that is user paste URL with the v query field, leave it as it is
   *
   * If user paste URL with si query field, remove si, and add v
   *
   */

  //add details to database
  return res.status(201).json({
    status: "",
    message: "",
  });
};

module.exports = addSongToDatabaseController;
