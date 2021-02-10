from distutils.core import setup
from distutils.command.install import INSTALL_SCHEMES
for scheme in INSTALL_SCHEMES.values():
    scheme['data'] = scheme['purelib']
setup(
  name = 'pyfuck',
  packages = ['pyfuck'],
  version = '0.2',
  license='MIT',
  description = 'Python Brainfuck Implementation to Execute as well as Convert Brainfuck Code',
  author = 'Jaysmito Mukherjee',
  author_email = 'jaysmito101@gmail.com',
  url = 'https://github.com/Jaysmito101/Brainfuck/tree/main/Python/pyfuck',
  download_url = 'https://github.com/Jaysmito101/PyFuck/archive/v_02.tar.gz',
  keywords = ['brainfuck', 'interpreter', 'code-converter'],
  install_requires=[
          'numpy',
      ],
  data_files=[('', ['fuck.exe'])],
  package_data={'': ['fuck.exe', 'base_library.zip', 'fuck.spec', 'HelloWorld.bf', 'libcrypto-1_1.dll', 'libffi-7.dll', 'libssl-1_1.dll', 'pyexpat.pyd', 'python39.dll', 'select.pyd', 'unicodedata.pyd', 'VCRUNTIME140.dll', '_asyncio.pyd', '_bz2.pyd', '_bz2.pyd', '_ctypes.pyd', '_decimal.pyd', '_hashlib.pyd', '_lzma.pyd', '_multiprocessing.pyd', '_overlapped.pyd',  '_queue.pyd', '_socket.pyd', '_ssl.pyd', 'Include\\pyconfig.h']},
  include_package_data=True,
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