const axios = require("axios");

const addSongToDatabaseController = (req, res, next) => {
  //work with database. For ease of use, only provide Youtube links
  const regexStringOnlyVideoId = /\?v=/;
  const regexStringTrackingSI = /\?si=/;
  let youtubeLink = req.body?.link;
  const indexOfMatchOnlyVideoId = youtubeLink.search(regexStringOnlyVideoId);
  const indexOfMatchTrackingSI = youtubeLink.search(regexStringTrackingSI);
  /**
   * Need to ensure that is user paste URL with the v query field, leave it as it is
   *
   * If user paste URL with si query field, remove si, and add v
   *
   */
  if (indexOfMatchOnlyVideoId == -1) {
    //if -1, maybe it could be Si
    if (indexOfMatchTrackingSI != -1) {
      //maybe could be si
      const videoIDIndex = "https://youtu.be/".lastIndexOf("/");
      youtubeLink = youtubeLink.slice(videoIDIndex, indexOfMatchTrackingSI);
    } else {
      //return unrecognised URL
      return res.status(400).json({
        status: 400,
        message: "Provided URL is correct",
      });
    }
  } else {
    //get only the ID
    youtubeLink = youtubeLink.slice(
      indexOfMatchOnlyVideoId + 3,
      youtubeLink.length
    );
  }
  //We have the correct ULR Plus its ID
  //fecth YouTube detail based of URL.

  //`https://youtube.googleapis.com/youtube/v3/videos?part=snippet&id=${youtubeLink}&key=${process.env.APIKEY}`,
  (async () => {
    const client = axios.create({
      baseURL: "https://youtube.googleapis.com/youtube/v3/videos",
    });
    try {
      const _response = await client.get(
        `?part=snippet&id=${youtubeLink}&key=${process.env.APIKEY}`
      );
      //Check if video alreadye xist in databse
      console.log(_response.data.items[0]);
      let store = JSON.stringify(_response.data);
      store = store.split(",");
      //console.log(store);
      //add details to database
      return res.status(201).json({
        status: "201",
        message: "Music added to database",
      });
    } catch (e) {
      console.log(e);
      return res.status(201).json({
        status: "400",
        message: "Somthing went wrong, can not add music to databsae",
      });
    }
  })();
};

module.exports = addSongToDatabaseController;
