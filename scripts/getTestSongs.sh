#!/usr/bin/bash

#Tool to download copyright free music
#https://freemusicarchive.org/home

function getMusicFromFMA {
    local outputPath="../audio"
    local increment=0
    #make director
    mkdir ${outputPath}
    for i in ${@}
    do
    echo "Downloading audio ${i}"
    curl --output "${outputPath}/track${increment}.mp3" "${i}" #&
    echo "Done downloading"
    increment=$((increment+1))
    done
}

getMusicFromFMA https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/K9QjfOAXXoWvTemfM6D9SHrO0NhsgbGwy8udfI1d.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/xbNItFOTMZnnfTOLirYZF8THGSThgqFK6Y6ffUUg.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Cooper-Moore__Assif_Tsahar/tells_untold/Cooper-Moore__Assif_Tsahar_-_02_-_Tribes_Gathering.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/Music_for_Video/Cullah/Cullah_The_Wild/Cullah_-_10_-_No_Home.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/MC_Cullah/E-MC_Cullah/MC_Cullah_-_12_-_The_Power_of_Flight.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/MC_Cullah/E-MC_Cullah/MC_Cullah_-_05_-_Soul_Hip_Hop.mp3
#wait