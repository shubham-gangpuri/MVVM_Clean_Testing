# [PingLib](https://github.com/shubhamgangpuri/MVVM_Clean_Testing/tree/ping_library/pingLib) Android Ping library v1

## Introduction
Using PingLib Android library it will be the easiest way to check the health of an IP or Web URL.
Add this library in your project and get the health status of servers used in your application.


## Android Library v1
v1 is using [HTTPClient](https://mvnrepository.com/artifact/org.jbundle.util.osgi.wrapped/org.jbundle.util.osgi.wrapped.org.apache.http.client/4.1.2)


### Gradle / Maven dependency
This library main dependencies are:
- build.gradle
  - add to android {…}: compileOptions {
  ```
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
  ```
  }
  -  kotlinOptions {
   ```     
          jvmTarget = '1.8'
  ```
        }
  coroutines and http client:
  ```
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
    implementation 'org.jbundle.util.osgi.wrapped:org.jbundle.util.osgi.wrapped.org.apache.http.client:4.1.2'
  ``` 


## Use Library
You need to add the library as android library module 
  ```
    PingStream.Builder(CONTEXT, HOST).pings(NUMBER_OF_ATTEMPTS).doCaching(TRUE OR FALSE)
                            .port(POST_NUMBER).build()
    For getting the latency use -> getHostHealth()
  ```


### As a git submodule
Basically get this code and compile it having it integrated via a git submodule:

1. go into your own apps directory on the command line and add this lib as a submodule: ```https://github.com/shubhamgangpuri/MVVM_Clean_Testing/tree/ping_library/pingLib```
2. Import/Open your app in Android Studio

##  License

PingLib Android Library is available under MIT license. See [LICENSE.md](https://github.com/shubhamgangpuri/MVVM_Clean_Testing) with the full license text.

### Third party libraries
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Compatibility

PingLib Android library is valid for Android version 6.0 and up (with ```android:minSdkVersion="21"``` and ```android:targetSdkVersion="32"```).
