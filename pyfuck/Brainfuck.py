import sys
class Brainfuck:
	def __init__(self, code, outputStream=sys.stdout, inputStream=sys.stdin):
		self.code = code
		self.memory = [0]
		self.memPointer = 0
		self.isSyntax = False
		self.outStream = outputStream
		self.inStream = inputStream

	def checkSyntax(self):
		brackets = []
		cTemp = 0
		for instruction in self.code:
			if instruction == "[":
				brackets.append("[")
			elif instruction == "]":
				if len(brackets)==0:
					return False
				else:
					brackets.pop()
		return True

	def execute(self):
		if self.isSyntax == False:
			self.isSyntax = self.checkSyntax()
			if self.isSyntax == False:
				write("\nSyntax Error!\n")
				return
		i = 0
		while i<len(self.code):
			instruction = self.code[i]
			if instruction == "+":
				self.memory[self.memPointer] = self.memory[self.memPointer] + 1
			elif instruction == "-":
				self.memory[self.memPointer] = self.memory[self.memPointer] - 1
			elif instruction == ">":
				self.memPointer += 1
				if len(self.memory) == self.memPointer:
					self.memory.append(0)
			elif instruction == "<":
				if self.memPointer == 0:
					write("\nNegetive memory cannot be referenced!\n")
					return
				else:
					self.memPointer -= 1
			elif instruction == ".":
				self.write(chr(self.memory[self.memPointer]))
			elif instruction == ",":
				self.memory[self.memPointer] = ord(self.inStream.readline()[0])
			elif instruction == "[":
				if self.memory[self.memPointer] == 0:
					i = self.getNextLClose(i+1)
			elif instruction == "]":
				if self.memory[self.memPointer] !=0:
					i = self.getPreviousLOpen(i-1)
			i+=1

	def getPreviousLOpen(self, current):
		cTemp = 0
		while current >= 0:
			if self.code[current] == "]":
				cTemp += 1
			elif self.code[current] == "[" and cTemp > 0:
				cTemp -= 1
			elif self.code[current] == "[" and cTemp == 0:
				return current
			current = current - 1;
		return -1

	def getNextLClose(self, current):
		cTemp = 0
		while current < len(self.code):
			if self.code[current] == "[":
				cTemp += 1
			elif self.code[current] == "]" and cTemp > 0:
				cTemp -= 1
			elif self.code[current] == "]" and cTemp == 0:
				return current
			current = current + 1;
		return -1

	def write(self, messgae = "\n"):
		if (sys.hexversion < 0x03000000):
			messgae = unicode(messgae)
			messgae = messgae.encode('utf-8')
		else:
			messgae = str(messgae)
		self.outStream.write(messgae)
		self.outStream.flush()