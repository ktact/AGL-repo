from sys import argv

emoji_list = [ 9725, 9726, 9748, 9749, 9800, 9801, 9802, 9803, 9804, 9805, 9806, 9807, 9808, 9809, 9810, 9811, 9855, 9875, 9889, 9898, 9899, 10175, 127183, 127761, 127762, 127763, 127764, 127765, 127766, 127767, 127768, 128045, 128046, 128049, 128053, 128512, 128513, 128514, 128515, 128516, 128517, 128518, 128519, 128520, 128521, 128522, 128523, 128524, 128525, 128526, 128527, 128528, 128529, 128530, 128531, 128532, 128533, 128534, 128535, 128536, 128537, 128538, 128539, 128540, 128541, 128542, 128543, 128544, 128545, 128546, 128547, 128549, 128550, 128551, 128552, 128553, 128554, 128555, 128557, 128558, 128559, 128560, 128561, 128562, 128563, 128564, 128565, 128566, 128567, 128568, 128569, 128570, 128571, 128572, 128573, 128574, 128575, 128576, 128579 ]

ttf = fontforge.open(argv[1])
for emoji in emoji_list:
    try:
        ttf.removeGlyph(emoji)
    except ValueError:
        pass
ttf.generate(argv[1])
