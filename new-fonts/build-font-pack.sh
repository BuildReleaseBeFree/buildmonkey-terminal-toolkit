#!/bin/sh

echo "Building Font Pack"

mkdir ./fonts-to-use

for FONT_FILE in `cat ./figlet-org-fonts-148.txt | grep -v '^#'`
do
  #echo "${FONT_FILE}"
  cp -v "${FONT_FILE}" ./fonts-to-use
done

for FONT_FILE in `cat ./java-de-fonts-263.txt | grep -v '^#'`
do
  #echo "${FONT_FILE}"
  cp -v "${FONT_FILE}" ./fonts-to-use
done

