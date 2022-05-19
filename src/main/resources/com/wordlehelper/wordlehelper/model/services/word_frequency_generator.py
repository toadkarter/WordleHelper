from wordfreq import word_frequency

word_frequency_dictionary = {}

with open("words.txt") as word_list:
    for word_line in word_list:
        word = word_line.lower().rstrip();
        print(f"Getting frequency of {word}...")
        frequency = word_frequency(word, "en")
        word_frequency_dictionary[word] = frequency

print()
print("***")
print("Done, starting to write to new text document")
print("***")
print()

word_frequencies = open("word_frequencies.txt", "a+")

for word in word_frequency_dictionary:
    print(f"Writing frequency of {word}")
    frequency = str(word_frequency_dictionary[word])
    word_frequencies.write(f"{word}:{frequency}\n")

print()
print("***")
print("All done!")
print("***")
print()