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
    curl --output "${outputPath}/track${increment}.mp3" "${i}" &
    echo "Done downloading"
    increment=$((increment+1))
    done
}

getMusicFromFMA https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/KcTIGNjCHXMBkFvGBXAyjfVLCvQ4NLZQidnfiKhZ.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/01HWIPso52L6soAVjWsXl3zchU3ea9PuoVfWhXJ8.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/SsUBTG6qSO0w8rNJSn5UfnIBXGFpYb3EDr23nPp1.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/CcyAGnrr25Pdmq4lXTnygAQ66Hkbx4pkKJNFMokh.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/YH4icYWu4J235jkVCIxvlCZ1yleA3uS7J2EoILVO.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/knk6DM8nTyiFIKYFXiq85sRlv1kSUm7ZwZ5oXAty.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/ppsQKwGq0QhymXIKokHVv39A2ckQJ9zD844Jjn5l.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/3G7bpy9M2BWMURt4HGNJlvWE0WmjoM32hpQVH7Vc.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Albert_Beger/Listening/Albert_Beger_-_01_-_What_A_Day.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/DRAM/Scott_Fields/Minaret_Minuets/Scott_Fields_-_Santa_on_a_Segway.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/Music_for_Video/Jazz_at_Mladost_Club/Jazz_Night/Jazz_at_Mladost_Club_-_09_-_C-mol_blues.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Jazz_at_Mladost_Club/Jazz_Night/Jazz_at_Mladost_Club_-_13_-_Konflikt.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Jazz_at_Mladost_Club/Jazz_Night/Jazz_at_Mladost_Club_-_01_-_No_More_Blues.mp3 https://files.freemusicarchive.org/storage-freemusicarchive-org/music/KEXP/Southeast_Engine/KEXP_Live_March_2011/Southeast_Engine_-_At_Least_We_Have_Each_Other_Live__KEXP.mp3
#seems to be 13
wait