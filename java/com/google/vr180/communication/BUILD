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

DEPS = [
    "//java/com/google/vr180/api",
    "//java/com/google/vr180/common",
    "//proto:camera_api_java_proto_lite",
    "@guava//jar",
    "@httpclient//jar",
    "@httpcore//jar",
    "@jsr305//jar",
    "@protobuf//jar",
]

android_library(
    name = "communication",
    srcs = glob(["**/*.java"]),
    deps = DEPS,
)
