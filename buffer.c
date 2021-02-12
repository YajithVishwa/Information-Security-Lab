#include <stdio.h>
#include <stdlib.h>
void main()
{
char *name;
char *command;
name=(char * ) malloc (10);
command=(char *) malloc (128);
printf("Ente r your name:");
gets(name );
printf("Hello %s\n",name);
system(command);
}
