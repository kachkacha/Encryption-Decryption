# Encryption-Decryption
command-line application for encrypting-decrypting Strings

arguments:
  * -mode: posiible values enc(encode) or dec(decode), enc by default
  * -alg: posiible values choose shift or unicode (just 2 for now), shift by default
  * -key: positive integer, 0 by default
  * -data: string to be encrypted or decrypted
  * -in: path to input file
  * -out: output file name
  
if you supply both -data and -in, string input is preffered over file input
if -out is not supplied, result is printed on standard output
