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

package(default_visibility = ["//visibility:public"])

licenses(["notice"])

cc_library(
    name = "vr_metadata_injector",
    srcs = [
        "vr_metadata_injector.cc",
    ],
    deps = [
        "//cpp/jni",
        "//cpp/video:edts_inject",
        "//cpp/video:modify_moov",
        "//cpp/video:replace_mett_with_camm",
        "//cpp/video:sdtp_inject",
        "//cpp/video:spherical_inject",
        "//cpp/video:stereo_mode",
        "//cpp/video/atoms:atom_moov",
        "@com_google_glog//:glog",
    ],
    alwayslink = 1,
)
