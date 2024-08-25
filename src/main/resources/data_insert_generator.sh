#!/bin/bash

# 파일 이름 설정
output_file="insert_posts.sql"

# 초기 파일 생성 및 시작
echo "Generating SQL insert script for 100,000 posts..."
echo "SET autocommit=0;" > $output_file
echo "START TRANSACTION;" >> $output_file

# Loop to generate SQL insert statements
for ((i=1; i<=100000; i+=1000))
do
    echo "INSERT INTO Post (title, content, member_id, board_id) VALUES" >> $output_file
    for ((j=i; j<i+1000 && j<=100000; j++))
    do
        title="Post title $j"
        content="This is the content of post number $j."
        member_id=1  # Assuming member_id is 1
        board_id=1   # Assuming board_id is 1

        if [ $j -eq $((i+999)) ] || [ $j -eq 100000 ]; then
            echo "('$title', '$content', $member_id, $board_id);" >> $output_file
        else
            echo "('$title', '$content', $member_id, $board_id)," >> $output_file
        fi
    done
done

# Commit the transaction
echo "COMMIT;" >> $output_file

echo "SQL script generated in $output_file"
