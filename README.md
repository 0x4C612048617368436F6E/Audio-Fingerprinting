# Audio fingerprinting

Learning all about audio fingerprinting by reimplementing it, but not trying to compete

# Language stack

1. Python - Actual CPU intensive fingerprinting logic (Using librosa for audio analysis and signal processing)
2. Java - FrontEnd (Android Application)
3. React - If I am successful with the frontend, might implement web based version
4. JavaScript (Node.JS and Express.JS) - For backend processing
5. postgreSQL - For database
6. Spotify API - Retrieve information once match is detected
7. Youtube

# IMPORTANT

Only copyright free audio has been used for this project, so quite limited. Seems like spotify allow music to be listened for free

# Write up (Draft)

In my recent post, I gave a high level defintion of what audio fingerprinting is, but did not go into the intracacies of how it work. This post does exactly that, it consists of a deeper explanation of audio fingerprints, the differnt phases involved as well as an exmaple implementation. My goal was not to replicate Shamza own implementation but rather to gain a deeper understand how it and share what i have learnt. I am by no means an expert within this field so if you find a flaw within this post, don't hesistate to correct me.

### How it all works

As previous stated, 'audio fingerprinting is a method used to identify or compare audio based on its unique characteristics, very similar to how a human fingerprint uniquely identifies a person.

Audio fingerprinting involves analysing a song, extracting key features such as frequency, amplitude, and duration. These are then mapped into a compact digital fingerprint.

When you try to identify a song, this fingerprint is compared against a database of known audio fingerprint to find the closest match.', and yes, this is correct, but lets dive deeper.

# What is an audio signal

An audio signal is simply a representation of sound either using a continuous representation known as an analoguge singal or a discrete set of value known as digital signal. Some important terms to be aware of include:

1. Frequency - Cycle per seconds
2. Amplititude - height of wave from rest to peak
3. Fourier Transform -
4. Fast Fourier Transform -

# Compare the difference between time domain and frequency domain

Two important representation of signals we will focus on here are time domain and frequency domain. Time domain is reprsentation of digital signal as a = f(t) where a is amplititude and t is time. In our case of audio fingerprinting, this doesn't provide as much information as we need, therefore, what we need to do is convert our time domain into frequency domain. This is done using FFT (Fast Fourier Transform). Being someone who is not an expert in maths, I decided to use a pre-written function, i.e. librosa.stft, and this simplay takes short windows and computes the fft of that. The result is a list of complex numbers each represeting the frequency, taking the absolute value of that gives us the intensity of the frequency compoennets that can then be represented as a spectogram

# Present conversion from one domain to another

# Some formula, but do not go too deep

### Some diagrams

### some testing stuff

### Conclusion

# Some stuff

https://medium.com/@NVSoftware/record-replay-and-visualize-raw-audio-data-in-android-93ad10262dd3
