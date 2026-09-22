# 导入三个python库
import requests
import os
import json

# 1. 获取官方英雄列表数据
url = "http://pvp.qq.com/web201605/js/herolist.json" # 定义字符串url
response = requests.get(url) # 调用库中的方法发起get请求，并使用response对象接受响应结果
# 确保中文不乱码
response.encoding = 'utf-8'  
hero_list = response.json()

# 2. 创建一个文件夹用来存放下载的图片，防止弄乱当前目录
save_dir = "glory_heroes_id_name"
if not os.path.exists(save_dir):
    os.makedirs(save_dir)

# 3. 遍历列表，逐个下载
for hero in hero_list:
    ename = hero['ename']  # 获取英雄数字ID
    cname = hero['cname']  # 获取英雄名称
    id_name = hero['id_name']
    
    # 拼接官方头像图片的URL (这里以默认头像为例)
    # 如果需要其他皮肤图片，可以修改URL规则
    img_url = f"http://game.gtimg.cn/images/yxzj/img201606/skin/hero-info/{ename}/{ename}-bigskin-1.jpg"
    
    try:
        # 发送请求获取图片内容
        img_response = requests.get(img_url, timeout=10)
        
        # 如果请求成功 (状态码为200)
        if img_response.status_code == 200:
            # 拼接保存路径，使用 cname 作为文件名
            file_path = os.path.join(save_dir, f"{id_name}.jpg")
            
            # 将图片内容写入文件
            with open(file_path, 'wb') as f:
                f.write(img_response.content)
                
            print(f"✅ 成功下载: {cname} ({ename})")
        else:
            print(f"❌ 下载失败(状态码异常): {cname} ({ename})")
            
    except Exception as e:
        print(f"❌ 下载出错: {cname} ({ename}), 错误信息: {e}")

print("\n🎉 所有英雄头像下载任务完成！")