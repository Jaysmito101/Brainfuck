from distutils.core import setup
setup(
  name = 'pyfuck',
  packages = ['pyfuck'],
  version = '0.1',
  license='MIT',
  description = 'Python Brainfuck Implementation to Execute as well as Convert Brainfuck Code',
  author = 'Jaysmito Mukherjee',
  author_email = 'jaysmito101@gmail.com',
  url = 'https://github.com/Jaysmito101/Brainfuck/tree/main/Python/pyfuck',
  download_url = '',
  keywords = ['brainfuck', 'interpreter', 'code-converter'],
  install_requires=[
          'numpy',
      ],
  classifiers=[
    'Development Status :: 3 - Alpha',
    'Intended Audience :: Developers',
    'Topic :: Software Development :: Build Tools',
    'License :: OSI Approved :: MIT License',
    'Programming Language :: Python :: 3',
    'Programming Language :: Python :: 3.4',
    'Programming Language :: Python :: 3.5',
    'Programming Language :: Python :: 3.6',
    'Programming Language :: Python :: 3.8',
    'Programming Language :: Python :: 3.9',
  ],
)