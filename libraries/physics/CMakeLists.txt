set(TARGET_NAME physics)
setup_hifi_library()
link_hifi_libraries(shared task workload fbx entities graphics shaders)
include_hifi_library_headers(networking)
include_hifi_library_headers(gpu)
include_hifi_library_headers(avatars)
include_hifi_library_headers(audio)
include_hifi_library_headers(octree)
include_hifi_library_headers(animation)
include_hifi_library_headers(model-networking)
include_hifi_library_headers(image)
include_hifi_library_headers(ktx)
include_hifi_library_headers(gpu)
include_hifi_library_headers(hfm)

target_bullet()
