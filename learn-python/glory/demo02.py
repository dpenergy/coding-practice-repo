from pypinyin import lazy_pinyin

# pypinyin库中的lazy_pinyin可以将中文转化为无音调的拼音
# 字符串的join方法可以将一个字符串数组按照指定的分割符拼接起来

# 1.读取文件
file_path = "D:/13_temp/res.txt"
content = ""
try:
    # 使用 with 语句可以确保文件在使用后自动关闭，即使发生异常也不会导致资源泄露
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()  # 读取全部内容并赋值给字符串变量 content
    
except FileNotFoundError:
    print(f"错误: 找不到文件 '{file_path}'")
except UnicodeDecodeError:
    print("错误: 文件编码不是 utf-8，请尝试其他编码（如 gbk）")

#print(content)
raw_lines = content.split("\n")
# for line in raw_lines:
#     print(line)

# first_line = raw_lines[0]
# columns = first_line.split(",")
# print(columns[1].split("\'")[1])
# # for column in columns :
# #     print(column)

for line in raw_lines :
    columns = line.split(",")
    name_pinyin = "".join(lazy_pinyin(columns[1].split("\'")[1]))
    parts = columns[0].split("/")
    parts[4] = name_pinyin + ".jpg'"
    columns[0] = "/".join(parts)
    new_line = ",".join(columns)
    print(new_line)