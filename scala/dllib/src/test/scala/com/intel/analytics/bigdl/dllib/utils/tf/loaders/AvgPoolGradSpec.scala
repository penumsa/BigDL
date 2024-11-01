/*
 * Copyright 2016 The BigDL Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intel.analytics.bigdl.dllib.utils.tf.loaders

import com.intel.analytics.bigdl.dllib.tensor.Tensor
import com.intel.analytics.bigdl.dllib.utils.T
import com.intel.analytics.bigdl.dllib.utils.tf.{PaddingType, TensorflowDataFormat, TensorflowSpecHelper}
import org.tensorflow.framework.{DataType, NodeDef}
import com.intel.analytics.bigdl.dllib.utils.tf.Tensorflow._

class AvgPoolGradSpec extends TensorflowSpecHelper {
  "Avg forward" should "be correct" in {
    compare[Float](
      NodeDef.newBuilder()
        .setName("avg_pool_grad_test")
        .setOp("AvgPoolGrad")
        .putAttr("data_format", TensorflowDataFormat.NHWC.value)
        .putAttr("T", typeAttr(DataType.DT_FLOAT))
        .putAttr("ksize", kernelAttr(4, 4, TensorflowDataFormat.NHWC))
        .putAttr("strides", strideAttr(1, 1, TensorflowDataFormat.NHWC))
        .putAttr("padding", PaddingType.PADDING_SAME.value),
      Seq(Tensor[Int](T(4, 32, 32, 3)), Tensor[Float](4, 32, 32, 3).rand()),
      0
    )

    compare[Float](
      NodeDef.newBuilder()
        .setName("avg_pool_grad_test")
        .setOp("AvgPoolGrad")
        .putAttr("data_format", TensorflowDataFormat.NHWC.value)
        .putAttr("T", typeAttr(DataType.DT_FLOAT))
        .putAttr("ksize", kernelAttr(4, 4, TensorflowDataFormat.NHWC))
        .putAttr("strides", strideAttr(1, 1, TensorflowDataFormat.NHWC))
        .putAttr("padding", PaddingType.PADDING_VALID.value),
      Seq(Tensor[Int](T(4, 32, 32, 3)), Tensor[Float](4, 29, 29, 3).rand()),
      0
    )
  }
}
