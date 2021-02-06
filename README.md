<img src="https://raw.githubusercontent.com/st235/StackLayoutManager/master/images/usage.gif" width="325" height="560">

# StackLayoutManager
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.st235/stacklayoutmanager/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.st235/stacklayoutmanager)

## Download it from

__Important: library was migrated from JCenter to MavenCentral__ 

It means that it may be necessary to add __mavenCentral__ repository to your repositories list

```groovy
allprojects {
    repositories {
        // your repositories

        mavenCentral()
    }
}
```

- Maven

```
<dependency>
  <groupId>com.github.st235</groupId>
  <artifactId>stacklayoutmanager</artifactId>
  <version>X.X.X</version>
  <type>pom</type>
</dependency>
```

- Gradle

```
implementation 'com.github.st235:stacklayoutmanager:X.X.X'
```

- Ivy

```
<dependency org='com.github.st235' name='stacklayoutmanager' rev='X.X.X'>
  <artifact name='stacklayoutmanager' ext='pom' ></artifact>
</dependency>
```

## License

```
MIT License

Copyright (c) 2020 Alexander Dadukin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
