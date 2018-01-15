## SOS
# Simple-OOPE-Shell
Virtually emulates a simplified file system and a shell-terminal to manipulate it.

This program was the course work for the course OOPE (Olio-ohjelmoinnin perusteet) @ UTA.
Unfortunately the program and its comments are in Finnish, because it was a requirement on the course.

Commands:
- md &lt;name&gt;
    - Creates a directory with &lt;name>
- mf &lt;name&gt; &lt;size&gt;
    - Creates a file with <name> and <size>
- cd &lt;name&gt;
    - Navigates to directory by <name>, if possible
- ls &lt;name&gt;
    - Lists contents of current directory if no &lt;name&gt; is given
    - If &lt;name&gt; is given, prints out information of a corresponding directory or file
- find
    - Recursively lists content of current directory and its sub-directories
- mv &lt;original&gt; &lt;new&gt;
    - Renames &lt;original&gt; file or directory to &lt;new&gt;
    - Cannot be used to move files or directories
- cp &lt;filename&gt; &lt;new&gt;
    - Creates a copy of file &lt;filename> with the name of &lt;new&gt;
- rm &lt;name&gt;
    - Removes a corresponding directory or file with &lt;name&gt;
- exit
    - Terminates the program