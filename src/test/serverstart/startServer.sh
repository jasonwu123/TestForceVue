#!/usr/bin/env bash
path=$1
if [[ ! -z "$path" ]]; then
	cd $path
else
	cd ~/Documents/GoodStart/force-vue
fi
npm run dev