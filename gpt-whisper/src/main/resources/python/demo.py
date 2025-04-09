import sys
import io
from faster_whisper import WhisperModel

# Force UTF-8 output
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

model_size = "large-v2"

# Use CPU with INT8 precision
model = WhisperModel(model_size, device="cpu", compute_type="int8")

segments, info = model.transcribe("C:/Users/HP/Documents/my_voice.aac", beam_size=5)

print("Detected language '%s' with probability %f" % (info.language, info.language_probability))

for segment in segments:
    print("[%.2fs -> %.2fs] %s" % (segment.start, segment.end, segment.text))
