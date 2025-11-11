//to configure cors
const allowedDomains = [undefined, ""];
const corsOption = {
  origin: (origin, callback) => {
    console.log("Origin is: ", origin);
    if (allowedDomains.indexOf(origin) != -1) {
      callback(null, true);
    } else {
      callback(new Error("Origin not allowed by cors"));
    }
  },
};

module.exports = corsOption;
