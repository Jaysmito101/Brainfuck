from Brainfuck import Brainfuck
import sys

if __name__ == '__main__':
	if len(sys.argv) <2:
		print(f"Expected 1 argument but found {len(sys.argv)-1}.")
		exit()
	if len(sys.argv) ==3:
		if sys.argv[1] == "/t":
			code = sys.argv[2]
			bfuck = Brainfuck(code)
			bfuck.execute()
			exit()
		else:
			print(f"Expected 2 arguments but found {len(sys.argv)-1}.")
			exit()
	filename = sys.argv[1]
	if filename.lower() == "/help":
		print("Help : ")
		exit()
	if filename.lower() == "/t":
		print(f"Expected 2 arguments but found {len(sys.argv)-1}.")
		exit()
	if(filename.endswith(".bf") or filename.endswith(".b") or filename.endswith(".bfuck") or filename.endswith(".fuck") ):
		pass
	else:
		filename += ".bf"
	try:
		with open(str(filename.lower()),'r',encoding = 'utf-8') as file:
			code = file.read()
			bfuck = Brainfuck(code)
			bfuck.execute()
	except FileNotFoundError:
		print("File " + filename.lower() + " not found.")
