## SOS
# Simple-OOPE-Shell
Virtually emulates a simplified file system and a shell-terminal to manipulate it.

This program was the course work for the course OOPE (Olio-ohjelmoinnin perusteet) @ UTA.
Unfortunately the program and its comments are in Finnish, because it was a requirement on the course.

Commands:
- md <name>
    - Creates a directory with <name>
- mf <name> <size>
    - Creates a file with <name> and <size>
- cd <name>
    - Navigates to directory by <name>, if possible
- ls <name>
    - Lists contents of current directory if no <name> is given
    - If <name> is given, prints out information of a corresponding directory or file
- find
    - Recursively lists content of current directory and its sub-directories
- mv <original> <new>
    - Renames <original> file or directory to <new>
    - Cannot be used to move files or directories
- cp <filename> <new>
    - Creates a copy of file <filename> with the name of <new>
- rm <name>
    - Removes a corresponding directory or file with <name>
- exit
    - Terminates the program