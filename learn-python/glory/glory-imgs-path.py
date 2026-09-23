# 重新处理照片路径
# 1.读取文件
file_path = "D:/13_temp/imgs-path.txt"
content = ""
try:
    # 使用 with 语句可以确保文件在使用后自动关闭，即使发生异常也不会导致资源泄露
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()  # 读取全部内容并赋值给字符串变量 content
    
except FileNotFoundError:
    print(f"错误: 找不到文件 '{file_path}'")
except UnicodeDecodeError:
    print("错误: 文件编码不是 utf-8，请尝试其他编码（如 gbk）")

# 2. 处理原始字符串
lines = content.split("\n")

for line in lines :
    print(line.replace("../assets/img","./src/assets/imgs"))