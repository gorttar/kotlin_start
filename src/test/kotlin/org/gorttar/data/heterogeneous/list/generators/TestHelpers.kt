package org.gorttar.data.heterogeneous.list.generators

internal val testSrcPath = "src/test/kotlin/$packagePath"
internal fun writeTestSrc(srcName: String, content: String): Unit = writeSrc(
    srcPath = testSrcPath,
    srcName = srcName,
    content = content
)