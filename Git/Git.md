# Git

<hr />

## 章节索引

- <a href="#overview">概述</a>

- <a href="#install">安装Git</a>
- <a href="#common-commands">Git常用命令</a>

## <span id="overview">1、概述</span>

<hr />

Git是一个免费的、开源的`分布式版本控制系统`，旨在快速高效地处理从小型项目到大型项目的所有内容。

Git 易于学习，占用空间小，性能快如闪电。 性能优于Subversion、CVS、Perforce 和 ClearCase 等 SCM 工具，具有廉价的本地分支、方便的暂存区和多个工作流等特性

### 1.1、什么是版本控制

<hr />

版本控制是一种记录文件内容变化，以便将来查阅特定版本修改内容的系统。

版本控制最重要的是可以记录文件修改的历史记录，从而让用户可以追溯到历史版本，方便切换版本。

### 1.2、为什么需要版本控制

<hr />

如果在开发团队中没有使用版本控制，多个开发人员共同负责同一个软件文档的开发，每个人在各自的机器上有整个软件文档的备份，并对之实施编程开发，在分别完成各自任务之后，再通过文本比对工具将各自机器上的不同版本的程序整合到一台机器上。没有进行版本控制或者版本控制本身缺乏正确的流程管理，在软件开发过程中将会引入很多问题，如软件代码的一致性、软件内容的冗余、软件过程的事物性、软件开发过程中的并发性、软件源代码的安全性，以及软件的整合等问题。 

### 1.3、版本控制工具

<hr />

版本控制工具分为两种，一种是集中式版本控制工具，一种是分布式式版本控制工具。

**集中式版本控制工具**

常见的集中式版本控制工具有CVS、`SVN(Subversion)`、VSS等等。

集中式版本控制系统都有一个单一的`集中管理`的服务器，保存所有文件的修订版本，而协同工作的人们都通过客户端连接到这台服务器，取出最新的文件或提交更新。多年以来，这已成为版本控制系统的标准做法。

这种做法带来了许多好处，每个人都可以在一定程序上看到项目中的其他人正在做些什么。而管理员也可以轻松掌控每个开发者的权限，并且管理一个集中化的版本控制系统，要远比在各个客户端维护本地数据库更轻松。但是这同时也有一个显而易见的缺点，如果中央服务器发生`单点故障`，服务器宕机一小时，那么在这一小时内，谁都无法提交更新，也就无法协同工作。

**分布式式版本控制工具**

常见的集中式版本控制工具有Git、Mercurial、Bazaar、Darcs等等。

对分布式版本控制系统来说，客户端提取的不是最新版本的文件快照，而是把代码仓库完整的镜像下来(本地库)。这样，任何一处协同工作的文件发生故障，都可以用其他客户端的本地仓库进行恢复。因为每个客户端的每一次文件提取操作，实际上都是对整个文件仓库的完整备份。

分布式的版本控制系统出现之后，解决了集中式版本控制系统的缺陷：

1. 服务器宕机的情况下也可以进行开发(因为版本控制是在本地进行的)
2. 每个客户端保存的都是整个完整的项目(包含历史记录，更加安全)

### 1.4、Git简史

<hr />

同生活中的许多伟大事物一样，Git 诞生于一个极富纷争大举创新的年代。

Linux 内核开源项目有着为数众多的参与者。 绝大多数的 Linux 内核维护工作都花在了提交补丁和保存归档的繁琐事务上（1991－2002年间）。 到 2002 年，整个项目组开始启用一个专有的分布式版本控制系统 BitKeeper 来管理和维护代码。

到了 2005 年，开发 BitKeeper 的商业公司同 Linux 内核开源社区的合作关系结束，他们收回了 Linux 内核社区免费使用 BitKeeper 的权力。 这就迫使 Linux 开源社区（特别是 Linux 的缔造者 Linus Torvalds）基于使用 BitKeeper 时的经验教训，开发出自己的版本系统。 他们对新的系统制订了若干目标：

- 速度
- 简单的设计
- 对非线性开发模式的强力支持（允许成千上万个并行开发的分支）
- 完全分布式
- 有能力高效管理类似 Linux 内核一样的超大规模项目（速度和数据量）

自诞生于 2005 年以来，Git 日臻成熟完善，在高度易用的同时，仍然保留着初期设定的目标。 它的速度飞快，极其适合管理大项目，有着令人难以置信的非线性分支管理系统

### 1.5、Git工作机制

<hr />

#### 1.5.1、四个区域

Git本地有四个工作区域：工作目录（Working Directory）、暂存区(Stage/Index)、资源库(Repository或Git Directory)、git仓库(Remote Directory)。文件在这四个区域之间的转换关系如下：

![1090617-20181008211557402-232838726](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/1090617-20181008211557402-232838726.png)

**Workspace(工作区)**：存放项目代码的地方，可以理解为项目目录

**Index / Stage(暂存区)**：用于临时存放你的改动，事实上它只是一个文件，保存即将提交到文件列表信息，一般存放在 **.git** 目录下的 `index`文件`(.git/index)`中

**Repository(本地库或版本库)**：安全存放数据的位置，这里面有你提交到所有版本的数据。其中HEAD指向最新放入仓库的版本，一般存放在 工作区的**.git**目录

**Remote(远程仓库)**：托管代码的服务器，可以简单的认为是你项目组中的一台电脑用于远程数据交换

#### 1.5.2、工作流程

git的工作流程一般是这样的：

1. 在工作目录中添加、修改文件
2. 将需要进行版本管理的文件放入暂存区域
3. 将暂存区域的文件提交到git仓库

因此，git管理的文件有三种状态：`已修改(modified)`，`已暂存(staged)`，`已提交(committed)`

### 1.6、Git和代码托管中心

<hr />

代码托管中心是基于网络服务器的远程代码仓库，一般习惯称为`远程库`

- 局域网
  - GitLab
- 互联网
  - GitHub(国外)
  - Gitee(国内，简称码云)

## <span id="install">2、安装Git</span>

<hr />

Git官网地址：https://git-scm.com/

![image-20230112190623676](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112190623676.png)

![image-20230112190816504](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112190816504.png)

![image-20230112190910904](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112190910904.png)

![image-20230112191031993](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112191031993.png)

![image-20230112191411313](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112191411313.png)

![image-20230112191540585](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112191540585.png)

![image-20230112191836587](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112191836587.png)

![image-20230112200153324](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112200153324.png)

![image-20230112210104603](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112210104603.png)

![image-20230112210321332](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112210321332.png)

![image-20230112210548998](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112210548998.png)

![image-20230112211355130](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230112211355130.png)

## <span id="common-commands">3、Git常用命令</span>

<hr />

| 命令                              | 功能                   | 格式                                                         | 参数说明                                                    |
| --------------------------------- | ---------------------- | ------------------------------------------------------------ | ----------------------------------------------------------- |
| ssh –keygen –t rsa                | 生成密钥               | ssh –keygen –t rsa –C [email]                                | email：邮箱地址                                             |
| git --version                     | 查看当前git版本        | git --version                                                | -                                                           |
| git config --global user.name     | 设置客户端全局用户名   | git config --global user.name username                       | username：用户名                                            |
| git config --global user.email    | 设置客户端全局用户邮箱 | git config --global user.email email                         | email：邮箱                                                 |
| git config --list                 | 查看客户端全局参数配置 | git config --list                                            | -                                                           |
| git config --local --list         | 列出repository配置     | git config --local --list                                    | -                                                           |
| git config --global --list        | 列出全局配置           | git config --global --list                                   | -                                                           |
| git config --system --list        | 列出系统配置           | git config --system --list                                   | -                                                           |
| git config --global color.ui auto |                        |                                                              |                                                             |
| git init                          | 初始化本地库           | git init                                                     | -                                                           |
| git status                        | 查看本地库状态         | git status                                                   | -                                                           |
| git add                           | 添加文件到暂存区       | git add [fileName]                                           | filename：文件名                                            |
| git commit                        | 提交文件到本地库       | git commit -m [commit log] fileName                          | commit log：提交日志                                        |
| git branch                        | 新建分支               | git branch [branchName]                                      | branchName：新的分支名                                      |
| git branch –D                     | 删除分支               | git branch –D [branchName]                                   | branchName：分支名                                          |
| git rm                            | 删除本地文件           | git rm --cached [fileName]：<br/>不删除物理文件，只将该文件从暂存区删除<br/>git rm -f [fileName]：<br/>删除物理文件，并且将该文件从暂存区删除 | fileName：文件名                                            |
| git rm                            | 删除本地目录           | git rm -r --cached [folderName]：<br/>不删除物理文件，只将该文件目录从暂存区删除<br/>git rm -rf [folderName]：<br/>删除物理文件，并且将该文件目录从暂存区删除 | folderName：文件目录名                                      |
| git clone                         | 克隆远程仓库           | git clone [-b] [branchName] url                              | -b：指定分支<br />branchName：分支名<br />url：远程仓库地址 |
| git reflog                        | 查看历史记录           | git reflog                                                   | -                                                           |
| git reset                         | 切换版本               | git reset --hard [version]                                   | version：版本号                                             |

### 3.1、配置用户签名

<hr />

**设置用户名**

```shell
/d/git-demo (master)
$ git config --global user.name username
```

**设置用户邮箱**

```shell
/d/git-demo (master)
$ git config --global user.email email
```

**注意：Git首次安装必须设置一下用户签名，否则无法提交代码。用户签名和GitHub(或其他代码托管中心)的账号没有任何关系。**

### 3.2、初始化本地库

<hr />

```shell
/d/git-demo (master)
$ git init
Initialized empty Git repository in D:/git-demo/.git/
```

### 3.3、查看本地库状态

<hr />

**首次查看本地库状态**

```shell
/d/git-demo (master)
$ git status
On branch master # 当前分支名称

No commits yet # 还没有提交

nothing to commit (create/copy files and use "git add" to track) # 没有任何提交
```

**添加文件**

```shell
/d/git-demo (master)
$ vim hello.txt

# 添加以下信息
hello,world0!
hello,world1!
hello,world2!
hello,world3!
hello,world4!
hello,world5!
hello,world6!
hello,world7!
```

**再次查看本地库状态**

```shell
/d/git-demo (master)
$ git status
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        hello.txt

nothing added to commit but untracked files present (use "git add" to track)
```

### 3.4、工作区添加到暂存区

<hr />

**将工作区文件添加到暂存区**

```shell
/d/git-demo (master)
$ git add hello.txt
warning: LF will be replaced by CRLF in hello.txt.
The file will have its original line endings in your working directory
```

**查看本地库状态**

```shell
/d/git-demo (master)
$ git status
On branch master

No commits yet

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   hello.txt
```

### 3.5、删除暂存区文件

<hr />

**删除暂存区文件，不删除物理文件**

```shell
/d/git-demo (master)
$ git rm --cached hello.txt
rm 'hello.txt'
```

**删除暂存区文件，同时删除物理文件**

```shell
/d/git-demo (master)
$ git rm -f hello.txt
rm 'hello.txt'
```

**删除暂存区目录，不删除物理文件目录**

```shell
/d/git-demo (master)
$ git rm -r --cached test/
rm 'test/aa.txt'
```

**删除暂存区目录，同时删除物理文件目录**

```shell
/d/git-demo (master)
$ git rm -rf test/
rm 'test/aa.txt'
```

### 3.6、暂存区提交到本地库

<hr />

**将暂存区文件提交到本地库**

```shell
/d/git-demo (master)
$ git commit -m "my first commit" hello.txt
warning: LF will be replaced by CRLF in hello.txt.
The file will have its original line endings in your working directory
[master (root-commit) e6601cb] my first commit
 1 file changed, 9 insertions(+)
 create mode 100644 hello.txt
```

**查看状态**

```shell
/d/git-demo (master)
$ git status
On branch master
nothing to commit, working tree clean
```

### 3.7、修改文件

<hr />

- 修改文件

  **查看当前文件内容**

  ```shell
  /d/git-demo (master)
  $ cat hello.txt
  hello,world0!
  hello,world1!
  hello,world2!
  hello,world3!
  hello,world4!
  hello,world5!
  hello,world6!
  hello,world7!
  ```

  **修改文件内容**

  ```shell
  /d/git-demo (master)
  $ echo "hello, world8!" >> hello.txt
  ```

  **查看本地库状态**

  ```shell
  /d/git-demo (master)
  $ git status
  On branch master
  Changes not staged for commit:
    (use "git add <file>..." to update what will be committed)
    (use "git restore <file>..." to discard changes in working directory)
          modified:   hello.txt
  
  no changes added to commit (use "git add" and/or "git commit -a")
  ```

- 将修改后的文件添加到暂存区

  ```shell
  /d/git-demo (master)
  $ git add hello.txt
  warning: LF will be replaced by CRLF in hello.txt.
  The file will have its original line endings in your working directory
  ```

- 将修改后的文件提交到本地库

  ```shell
  /d/git-demo (master)
  $ git commit -m "update hello.txt" hello.txt
  warning: LF will be replaced by CRLF in hello.txt.
  The file will have its original line endings in your working directory
  [master 293b928] update hello.txt
   1 file changed, 1 insertion(+)
  ```

### 3.8、历史版本

<hr />

#### 3.8.1、查看历史版本

- 查看版本信息

  ```shell
  /d/git-demo (master)
  $ git reflog
  293b928 (HEAD -> master) HEAD@{0}: commit: update hello.txt
  527ccb7 HEAD@{1}: commit (initial): my first commit
  ```

- 查看版本详细信息

  ```shell
  /d/git-demo (master)
  $ git log
  commit 293b92879f536ac5a95b5081b5c823992f6fcf16 (HEAD -> master)
  Author: elonlo <elonlo1024@gmail.com>
  Date:   Sat Jan 14 11:14:56 2023 +0800
  
      update hello.txt
  
  commit 527ccb72804c2c5f0c9dc79787fa9b7483a95cdf
  Author: elonlo <elonlo1024@gmail.com>
  Date:   Sat Jan 14 11:11:21 2023 +0800
  
      my first commit
  ```

#### 3.8.2、切换本地库版本

**查看当前本地库版本**

```shell
/d/git-demo (master)
$ git log --oneline
293b928 (HEAD -> master) update hello.txt
527ccb7 my first commit
```

**查看当前文件内容**

```shell
/d/git-demo (master)
$ cat hello.txt
hello,world0!
hello,world1!
hello,world2!
hello,world3!
hello,world4!
hello,world5!
hello,world6!
hello,world7!
hello,world8!
```

**切换本地库版本**

```shell
/d/git-demo (master)
$ git reset --hard 527ccb7
HEAD is now at 527ccb7 my first commit
```

**再次查看当前文件内容**

```shell
/d/git-demo (master)
$ cat hello.txt
hello,world0!
hello,world1!
hello,world2!
hello,world3!
hello,world4!
hello,world5!
hello,world6!
hello,world7!
```

**查看本地库版本信息**

```shell
/d/git-demo (master)
527ccb7 (HEAD -> master) HEAD@{0}: reset: moving to 527ccb7
293b928 HEAD@{1}: commit: update hello.txt
527ccb7 (HEAD -> master) HEAD@{2}: commit (initial): my first commit
```

## 4、Git分支操作

<hr />

几乎每一种版本控制系统都以某种形式支持分支，一个分支代表一条独立的开发线。使用分支意味着你可以从开发主线上分离开来，然后在不影响主线的同时继续工作。

### 4.1、什么是分支

在版本控制中，我们可以同时开发不同的任务，为了不影响`主线(master)分支`，可以创建每个任务的单独分支。使用分支意味着程序员可以把自己的工作从开发主线上分离开来，开发自己的分支时，不会影响主线分支的运行。

Git分支实际上是指向更改`快照`的`指针`。

### 4.2、分支的好处

同时并行推进多个功能开发，提高开发效率。

各个分支在开发过程中，如果某一个分支开发失败，不会对其他分支造成任何影响。

### 4.3、分支操作

| 命令                                            | 作用                                           |
| ----------------------------------------------- | ---------------------------------------------- |
| git branch                                      | 列出所有的分支                                 |
| git branch -r                                   | 列出所有的远端分支                             |
| git branch <new-branch>                         | 基于当前分支创建新分支                         |
| git branch --track <new-branch> <remote-branch> | 基于远程分支创建新的可追溯的分支               |
| git checkout <branch>                           | 切换分支                                       |
| git checkout -b <branch>                        | 创建并切换到新分支                             |
| git branch -d <branch>                          | 删除本地分支                                   |
| git branch -D <branch>                          | 强制删除一个本地分支(*将会丢失未合并的修改！*) |

#### 4.3.1、查看分支

```shell

/d/git-demo (master)
$ git branch -v
* master 527ccb7 my first commit # *表示当前分支
```

#### 4.3.2、创建分支

```shell
/d/git-demo (master)
# 创建hot-fix分支
$ git branch hot-fix

# 查看所有分支
$ git branch -v
  hot-fix 527ccb7 my first commit
* master  527ccb7 my first commit
```

#### 4.3.3、切换分支

```shell
/d/git-demo (master)
$ git checkout hot-fix
Switched to branch 'hot-fix'

/d/git-demo (hot-fix)
```

#### 4.3.4、修改分支

```shell
/d/git-demo (hot-fix)
# 向hello.txt中追加"haha"字符串
$ echo "haha" >> hello.txt

# 将hello.txt添加到暂存区
$ git add hello.txt
warning: LF will be replaced by CRLF in hello.txt.
The file will have its original line endings in your working directory

# 将hello.txt提交到本地库
$ git commit -m "This is the first commit of the hot-fix branch"
[hot-fix 684553d] This is the first commit of the hot-fix branch
 1 file changed, 1 insertion(+)
```

#### 4.3.5、合并分支

```shell
/d/git-demo (hot-fix)
# 从hot-fix分支切换到master分支
$ git checkout master
Switched to branch 'master'

# 查看当前hello.txt文件内容
/d/git-demo (master)
$ cat hello.txt
hello,world0!
hello,world1!
hello,world2!
hello,world3!
hello,world4!
hello,world5!
hello,world6!
hello,world7!

# 将hot-fix分支合并到master分支
$ git merge hot-fix
Updating 527ccb7..684553d
Fast-forward
 hello.txt | 1 +
 1 file changed, 1 insertion(+)
 
# 查看当前hello.txt文件内容
$ cat hello.txt
hello,world0!
hello,world1!
hello,world2!
hello,world3!
hello,world4!
hello,world5!
hello,world6!
hello,world7!
haha
```

#### 4.3.6、产生冲突

- hot-fix分支修改hello.txt文件

  ```shell
  /d/git-demo (hot-fix)
  # 向hello.txt文件追加内容
  $ echo "update hello.txt of the hot-fix branch" >> hello.txt
  
  # 将hello.txt添加到暂存区
  $ git add hello.txt
  warning: LF will be replaced by CRLF in hello.txt.
  The file will have its original line endings in your working directory
  
  # 将hello.txt提交到本地库
  $ git commit -m "update hello.txt -->hot-fix" hello.txt
  warning: LF will be replaced by CRLF in hello.txt.
  The file will have its original line endings in your working directory
  [hot-fix f8b122e] update hello.txt -->hot-fix
   1 file changed, 1 insertion(+)
  ```

- master分支也修改hello.txt文件

  ```shell
  /d/git-demo (master)
  # 向hello.txt文件追加内容
  $ echo "update hello.txt of the master branch" >> hello.txt
  
  # 将hello.txt添加到暂存区
  $ git add hello.txt
  warning: LF will be replaced by CRLF in hello.txt.
  The file will have its original line endings in your working directory
  
  # 将hello.txt提交到本地库
  $ git commit -m "update hello.txt -->master" hello.txt
  warning: LF will be replaced by CRLF in hello.txt.
  The file will have its original line endings in your working directory
  [master c9132e0] update hello.txt -->master
   1 file changed, 1 insertion(+)
   
  # 将hot-fix分支合并到master分支
  $ git merge hot-fix
  Auto-merging hello.txt
  CONFLICT (content): Merge conflict in hello.txt
  Automatic merge failed; fix conflicts and then commit the result.
  /d/git-demo (master|MERGING)
  ```

#### 4.3.7、解决冲突

- 保留需要的代码，删除冲突的代码

  ```shell
  /d/git-demo (master|MERGING)
  # 查看当前hello.txt文件内容
  $ cat hello.txt
  hello,world0!
  hello,world1!
  hello,world2!
  hello,world3!
  hello,world4!
  hello,world5!
  hello,world6!
  hello,world7!
  haha
  <<<<<<< HEAD
  update hello.txt of the master branch
  =======
  update hello.txt of the hot-fix branch
  >>>>>>> hot-fix
  
  # 编辑hello.txt文件内容
  $ vim hello.txt
  
  # 保留需要的代码,删除冲突的代码,重新查看hello.txt文件内容
  $ cat hello.txt
  hello,world0!
  hello,world1!
  hello,world2!
  hello,world3!
  hello,world4!
  hello,world5!
  hello,world6!
  hello,world7!
  haha
  update hello.txt of the master branch
  update hello.txt of the hot-fix branch
  ```

- 添加到暂存区

  ```shell
  /d/git-demo (master|MERGING)
  $ git add hello.txt
  ```

- 提交到本地库

  ```shell
  /d/git-demo (master|MERGING)
  $ git commit -m "update merge"
  [master c3c14ad] update merge
  
  /d/git-demo (master)
  
  # 查看当前hello.txt文件内容
  $ cat hello.txt
  hello,world0!
  hello,world1!
  hello,world2!
  hello,world3!
  hello,world4!
  hello,world5!
  hello,world6!
  hello,world7!
  haha
  update hello.txt of the master branch
  update hello.txt of the hot-fix branch
  ```

  **注意：修改冲突文件内容后，提交文件到本地库结尾不能带文件名，否则会报`fatal: cannot do a partial commit during a merge.`错误**

### 4.4、Git分支图解

![git-branch](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/git-branch.png)

其中，红色背景的矩形表示分支名称，白色圆角矩形C0-C5表示提交的记录。

**注意：所有分支的修改记录最终都是合并到`master分支`的最后提交记录中**

## 5、Git团队协作机制

<hr />

### 5.1、团队内协作

团队内协作的流程大致如下图所示：

![git-internal-teamwork](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/git-internal-teamwork.jpg)

流程详细介绍如下：

1. 项目开发完成之后，由项目经理推送(push)项目到代码托管中心
2. 如果项目临时修改某些需求，则程序员先从代码托管中心克隆(clone)整个项目到本地库，再进行修改
3. 开发人员修改完成之后，再推送(push)项目到代码托管中心
4. 此时如果项目经理需要查看项目中修改的部分代码，可以从代码托管中心拉取(pull)整个项目到本地库

### 5.2、跨团队协作

跨团队协作的流程大致如下图所示：

![git-internal-cross-team-work-update](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/git-internal-cross-team-work-update.jpg)

流程详细介绍如下：

1. 张三创建了一个本地库并推送到代码托管中心的远程库
2. 因为有一个技术难题很久没有解决，所以找到了自己的好朋友李四帮忙
3. 李四fork了一份张三的远程库代码到自己的远程库，并从自己的远程库clone代码到本地库进行修改
4. 李四修改完成后将本地库代码push到自己的远程库
5. 李四的远程库向张三的远程库提交了一个PR(Pull Request)请求
6. 张三审核代码
7. 张三审核完成后Merge代码到自己的远程库
8. 张三Pull代码到自己的本地库
9. 张三团队内的王五也将代码Pull到自己的本地库

## 6、GitHub操作

<hr />

GitHub官网地址：https://github.com/

### 6.1、创建远程仓库

准备三个GitHub账号，没有的可以去官网注册。

| 账号         | 姓名 | 验证邮箱             |
| ------------ | ---- | -------------------- |
| elon-lo      | 张三 | 1223209xxx@qq.com    |
| butterfly666 | 李四 | 228311xxx@qq.com     |
| elon-July    | 王五 | elonlo1xxx@gmail.com |

- 登录张三账号并创建远程仓库

  ![image-20230401121137931](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230401121137931.png)

- 填写仓库信息

  ![image-20230401121446437](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230401121446437.png)

- 创建成功

  ![image-20230401121827380](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230401121827380.png)

### 6.2、远程仓库操作

| 命令名称                           | 作用                                                     |
| ---------------------------------- | -------------------------------------------------------- |
| git remote -v                      | 查看当前所有远程地址别名                                 |
| git remote add alias remoteAddress | 给远程仓库创建别名                                       |
| git push alias branch              | 推送本地分支上的内容到远程分支                           |
| git clone remoteAddress            | 将远程仓库的内容克隆到本地[默认是master分支或者main分支] |
| git clone -b branch remoteAddress  | 将远程仓库指定分支上的内容克隆到本地                     |
| git pull alias branch              | 将远程仓库分支最新内容拉取后与当前本地分支直接合并       |

- **git clone branch remoteAddress**

  

- 

- 

- **注意：如果远程仓库内容为空，即只有一个`.git`文件时，不能使用`git clone -b branch remoteAddress`克隆代码，只能使用`git clone remoteAddress`方式**

  
