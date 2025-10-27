# Audio fingerprinting

Learning all about audio fingerprinting by reimplementing it

# Langiage stack

1. Python - Actual CPU intensive fingerprinting logic (Using librosa for audio analysis and signal processing)
2. Java - FrontEnd (Android Application)
3. React - If i am successful with the frontend, might implement web based version
4. JavaScript (Node.JS and Express.JS) - For backend processing
5. postgreSQL - For database
6. Spofify API - Retrieve information once match is detected

# IMPORTANT

Only copyright free audio has been used for this project, so quite limited. Seems like spotify allow music to be listened for free

# Write up (Draft)

In my recent post, I gave a high level defintion of what audio fingerprinting is, but did not go into the intracacies of how it work. This post does exactly that, it consists of a deeper explanation of audio fingerprints, the differnt phases involved as well as an exmaple implementation. My goal was not to replicate Shamza own implementation but rather to gain a deeper understand how it and share what i have learnt.

### How it all works

As previous stated, 'audio fingerprinting is a method used to identify or compare audio based on its unique characteristics, very similar to how a human fingerprint uniquely identifies a person.

Audio fingerprinting involves analysing a song, extracting key features such as frequency, amplitude, and duration. These are then mapped into a compact digital fingerprint.

When you try to identify a song, this fingerprint is compared against a database of known audio fingerprint to find the closest match.', and yes, this is correct, but lets dive deeper. We will begin from the basis

# What

### Some diagrams

### some testing stuff

### Conclusion
