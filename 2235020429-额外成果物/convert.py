import subprocess

def convert_mp4_to_avi(input_file, output_file):
    # 使用 FFmpeg 将 MP4 转换为 AVI
    command = [
        'ffmpeg',
        '-i', input_file,  # 输入文件
        '-c', 'copy',      # 复制流（无损转换）
        output_file        # 输出文件
    ]

    try:
        subprocess.run(command, check=True)
        print(f"Conversion successful: {output_file}")
    except subprocess.CalledProcessError as e:
        print(f"An error occurred: {e}")

# 使用示例
input_file = 'input.mp4'
output_file = 'output.avi'
convert_mp4_to_avi(input_file, output_file)
