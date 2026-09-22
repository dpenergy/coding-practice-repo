# 1.导入需要的库
import requests
import json
import os

# 2. 发起get请求
url = "http://pvp.qq.com/web201605/js/herolist.json"
response = requests.get(url)

# 3. 获取需要的结果
response.encoding = "utf-8"
hero_list = response.json()

print(os.getcwd())

# 4. 打印结果
for hero in hero_list:
    print(hero['id_name'])