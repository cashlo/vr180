# Copyright 2018 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

workspace(name = "vr180")

load("@bazel_tools//tools/build_defs/repo:maven_rules.bzl", "maven_aar")
load("@bazel_tools//tools/build_defs/repo:maven_rules.bzl", "maven_jar")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")


android_sdk_repository(
    name = "androidsdk",
    api_level = 33,
)

android_ndk_repository(
    name = "androidndk",
)

# Google Maven Repository
GMAVEN_TAG = "20181128-1"  # or the tag from the latest release

http_archive(
    name = "gmaven_rules",
    strip_prefix = "gmaven_rules-%s" % GMAVEN_TAG,
    url = "https://github.com/bazelbuild/gmaven_rules/archive/%s.tar.gz" % GMAVEN_TAG,
)

load("@gmaven_rules//:gmaven.bzl", "gmaven_rules")

gmaven_rules()

http_archive(
    name = "com_google_absl",
    # sha256 = "84b4277a9b56f9a192952beca535313497826c6ff2e38b2cac7351a3ed2ae780",
    strip_prefix = "abseil-cpp-20240722.0",
    urls = ["https://github.com/abseil/abseil-cpp/releases/download/20240722.0/abseil-cpp-20240722.0.tar.gz"],
)

http_archive(
    name = "com_google_googletest",
    sha256 = "5aaa5d566517cae711e2a3505ea9a6438be1b37fcaae0ebcb96ccba9aa56f23a",
    strip_prefix = "googletest-b4d4438df9479675a632b2f11125e57133822ece",
    urls = ["https://github.com/google/googletest/archive/b4d4438df9479675a632b2f11125e57133822ece.zip"],
)

http_archive(
    name = "com_google_protobuf",
    sha256 = "826425182ee43990731217b917c5c3ea7190cfda141af4869e6d4ad9085a740f",
    strip_prefix = "protobuf-3.5.1",
    urls = [
        "https://mirror.bazel.build/github.com/google/protobuf/archive/v3.5.1.tar.gz",
        "https://github.com/google/protobuf/archive/v3.5.1.tar.gz",
    ],
)

http_archive(
    name = "com_google_protobuf_javalite",
    sha256 = "38458deb90db61c054b708e141544c32863ab14a8747710ba3ee290d9b6dab92",
    strip_prefix = "protobuf-javalite",
    urls = ["https://github.com/google/protobuf/archive/javalite.zip"],
)

http_archive(
    name = "robolectric",
    sha256 = "f7b8e08f246f29f26fddd97b7ab5dfa01aaa6170ccc24b9b6a21931bde01ad6f",
    strip_prefix = "robolectric-bazel-4.12.2",
    urls = ["https://github.com/robolectric/robolectric-bazel/releases/download/4.12.2/robolectric-bazel-4.12.2.tar.gz"],
)
load("@robolectric//bazel:robolectric.bzl", "robolectric_repositories")

robolectric_repositories()

http_archive(
    name = "eigen",
    build_file = "//:cpp/third_party/BUILD.eigen",
    sha256 = "d956415d784fa4e42b6a2a45c32556d6aec9d0a3d8ef48baee2522ab762556a9",
    strip_prefix = "eigen-eigen-fd6845384b86",
    urls = [
        "https://mirror.bazel.build/bitbucket.org/eigen/eigen/get/fd6845384b86.tar.gz",
        "https://bitbucket.org/eigen/eigen/get/fd6845384b86.tar.gz",
    ],
)

http_archive(
    name = "libjpeg_turbo",
    build_file = "//:cpp/third_party/BUILD.libjpeg_turbo",
    sha256 = "0a3195506b92f0c29e4fa5f3f5387f531c390a04e74615895443176883b040b8",
    strip_prefix = "libjpeg-turbo-43ce78e0321da44fe359f40a847fe79d2de06d4c",
    urls = ["https://github.com/libjpeg-turbo/libjpeg-turbo/archive/43ce78e0321da44fe359f40a847fe79d2de06d4c.tar.gz"],
)

http_archive(
    name = "com_google_glog",
    build_file = "//:cpp/third_party/BUILD.glog",
    sha256 = "19b8b73719c1a188899f728787dcf4899e72a259bef66a9d66e116ccedfaee02",
    strip_prefix = "glog-781096619d3dd368cfebd33889e417a168493ce7",
    urls = ["https://github.com/google/glog/archive/781096619d3dd368cfebd33889e417a168493ce7.zip"],
)

http_archive(
    name = "com_google_xmpmeta",
    build_file = "//:cpp/third_party/BUILD.xmpmeta",
    sha256 = "c958dd1d326bdfbd3da726a86e3fe9733ba8eb40ef303275063a1e6c80aaf00a",
    strip_prefix = "xmpmeta-73671eccaef4879bb89fa98ee3e50514760f6c97",
    urls = ["https://github.com/google/xmpmeta/archive/73671eccaef4879bb89fa98ee3e50514760f6c97.zip"],
)

# libxml is a dependency for com_google_xmpmeta
http_archive(
    name = "libxml",
    build_file = "//:cpp/third_party/BUILD.libxml",
    sha256 = "f63c5e7d30362ed28b38bfa1ac6313f9a80230720b7fb6c80575eeab3ff5900c",
    strip_prefix = "libxml2-2.9.7",
    urls = [
        "https://mirror.bazel.build/xmlsoft.org/sources/libxml2-2.9.7.tar.gz",
        "http://xmlsoft.org/sources/libxml2-2.9.7.tar.gz",
    ],
)

maven_aar(
    name = "glide",
    artifact = "com.github.bumptech.glide:glide:4.8.0",
    sha1 = "969a4da7a313e72111ef1d00ad3aba16f6772856",
)

maven_aar(
    name = "glide_gifdecoder",
    artifact = "com.github.bumptech.glide:gifdecoder:4.8.0",
    sha1 = "92e683243fb7efcdaa2f372c6ebeb20db8e1880e",
)

maven_aar(
    name = "webrtc",
    artifact = "com.dafruits:webrtc:123.0.0",
)

maven_aar(
    name = "rxandroid2",
    artifact = "io.reactivex.rxjava2:rxandroid:2.1.0",
    sha1 = "1d05d7d192aa5f6989d352020bde5b8e2dfc45b2",
)

maven_jar(
    name = "protobuf",
    artifact = "com.google.protobuf:protobuf-java:3.5.1",
    sha1 = "8c3492f7662fa1cbf8ca76a0f5eb1146f7725acd",
)

maven_jar(
    name = "autovalue",
    artifact = "com.google.auto.value:auto-value:1.5.3",
    sha1 = "514df6a7c7938de35c7f68dc8b8f22df86037f38",
)

maven_jar(
    name = "autovalue_annotations",
    artifact = "com.google.auto.value:auto-value-annotations:1.6.2",
    sha1 = "ed193d86e0af90cc2342aedbe73c5d86b03fa09b",
)

maven_jar(
    name = "com_google_truth",
    artifact = "com.google.truth:truth:0.39",
    sha1 = "bd1bf5706ff34eb7ff80fef8b0c4320f112ef899",
)

maven_jar(
    name = "glide_annotations",
    artifact = "com.github.bumptech.glide:annotations:4.8.0",
    sha1 = "c4c9e79eb2bfeb9059fce55020c6b237402285f6",
)

maven_jar(
    name = "glide_disklrucache",
    artifact = "com.github.bumptech.glide:disklrucache:4.8.0",
    sha1 = "a7448551cb3edd61bcf3d1b1136b670e630441cf",
)

maven_jar(
    name = "glide_compiler",
    artifact = "com.github.bumptech.glide:compiler:4.8.0",
    sha1 = "69b059a9dd19bf8bd722da4cf6306fbb1a6e68cc",
)

maven_jar(
    name = "guava",
    artifact = "com.google.guava:guava:26.0-android",
    sha1 = "ef69663836b339db335fde0df06fb3cd84e3742b",
)

# hamcrest_core is a dependency for junit
maven_jar(
    name = "hamcrest_core",
    artifact = "org.hamcrest:hamcrest-core:1.3",
    sha1 = "42a25dc3219429f0e5d060061f71acb49bf010a0",
)

maven_jar(
    name = "httpclient",
    artifact = "org.apache.httpcomponents:httpclient:4.5.6",
    sha1 = "1afe5621985efe90a92d0fbc9be86271efbe796f",
)

maven_jar(
    name = "httpcore",
    artifact = "org.apache.httpcomponents:httpcore:4.4.10",
    sha1 = "acc54d9b28bdffe4bbde89ed2e4a1e86b5285e2b",
)

maven_jar(
    name = "jodatime",
    artifact = "joda-time:joda-time:2.10.1",
    sha1 = "9ac3dbf89dbf2ee385185dd0cd3064fe789efee0",
)

maven_jar(
    name = "jsr305",
    artifact = "com.google.code.findbugs:jsr305:3.0.1",
    sha1 = "f7be08ec23c21485b9b5a1cf1654c2ec8c58168d",
)

# Included for Assert.assertThrows
maven_jar(
    name = "junit",
    artifact = "junit:junit:jar:4.13-beta-1",
    sha1 = "1bc4a3b4a2d01a08c3a2cc8143666565b846ed17",
)

maven_jar(
    name = "mockito",
    artifact = "org.mockito:mockito-all:1.9.5",
    sha1 = "79a8984096fc6591c1e3690e07d41be506356fa5",
)

maven_jar(
    name = "okhttp",
    artifact = "com.squareup.okhttp3:okhttp:3.11.0",
    sha1 = "75966e05a49046ca2ae734e5626f28837a8d1e82",
)

# okio is a dependency for okhttp
maven_jar(
    name = "okio",
    artifact = "com.squareup.okio:okio:1.13.0",
    sha1 = "a9283170b7305c8d92d25aff02a6ab7e45d06cbe",
)

maven_jar(
    name = "rxjava2",
    artifact = "io.reactivex.rxjava2:rxjava:2.2.3",
    sha1 = "f829e7c489f5b3586bca2199c9017a6d6e1e01be",
)

maven_jar(
    name = "reactivestreams",
    artifact = "org.reactivestreams:reactive-streams:1.0.2",
    sha1 = "323964c36556eb0e6209f65c1cef72b53b461ab8",
)

RULES_JVM_EXTERNAL_TAG = "6.3"
RULES_JVM_EXTERNAL_SHA = "c18a69d784bcd851be95897ca0eca0b57dc86bb02e62402f15736df44160eb02"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/releases/download/%s/rules_jvm_external-%s.tar.gz" % (RULES_JVM_EXTERNAL_TAG, RULES_JVM_EXTERNAL_TAG)
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_aar(
    name = "androidx_work_runtime",
    artifact = "androidx.work:work-runtime:2.7.0",
)

maven_install(
    name = "maven",
    artifacts = [
        "com.google.android.gms:play-services-ads-lite:23.3.0",  # Add any other dependencies here

    ],
    excluded_artifacts = ["androidx.work:work-runtime"],
    repositories = [
        "https://maven.google.com",  # Google Maven repository
        "https://repo1.maven.org/maven2",  # Maven Central
    ],
)