""let project_dir = '/home/sean/workspace/tfg'

set path=/home/sean/code/yuzz/**,..
set tags=/home/sean/code/yuzz/TAGS
execute 'cd /home/sean/code/yuzz/'
set complete=.,w,b,u,t,i,k,kspell

fun! UpdateTags() 
	cd /home/sean/code/yuzz/
	call system("etags --exclude='yui' -V --recurse .")
endfun
call UpdateTags()
