package com.daejol.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daejol.presentation.ui.utils.sp
import kotlin.math.max
import kotlin.math.min

/** CustomRichText에 담을 자식 클래스입니다.
 * @param text 단순히 텍스트를 담아주세요.
 * @param textStyle 스타일을 넣어주세요. []CustomTextStyle] 클래스를 사용해주세요.
 * @param endOfLine 다음 줄로 줄바꿈하고 싶을 때 true를 해주세요.
 */
@Deprecated("CustomRichText v1용으로 사용하던 클래스.")
open class RichText(
    val text: String = "",
    val textStyle: CustomTextStyle = CustomTextStyle(),
    val endOfLine: Boolean = false,
)

/**한 줄의 텍스트에 여러 스타일을 적용하고 싶을 때 사용합니다.
 *
 *    CustomRichText(
 *       listOf(
 *          RichText(text = "h", style = CustomStyle()),
 *          RichText(text = "ello")
 *          RichText(text = " world!", endOfLine = true)
 *       )
 *    )
 *
 * @param texts 텍스트와 텍스트 스타일을 담아줍니다. [RichText] 클래스를 사용해주고 list로 묶어주세요.
 **/
@Deprecated("Text를 이용해서 RichText를 만드는 방식에서 Layout을 이용해서 만들겠습니다.")
@Composable
fun CustomRichText(
    texts: List<RichText>,
    defaultTextSize: Float = 14F,
    defaultTextColor: Color = Black100,
    defaultFontFamily: FontFamily = Pretendard,
    defaultFontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Left
) {
    return Text(
        buildAnnotatedString {
            texts.forEach {
                //
                val spanStyle = SpanStyle(
                    color = it.textStyle.fontColor ?: defaultTextColor,
                    fontWeight = it.textStyle.fontWeight ?: defaultFontWeight,
                    fontSize = it.textStyle.fontSize?.sp ?: defaultTextSize.sp,
                    fontFamily = it.textStyle.fontFamily ?: defaultFontFamily,
                    background = Orange100,

                    )

                withStyle(style = spanStyle) {
                    append(it.text)
                }

                if (it.endOfLine) {
                    append("\n")
                }
            }
        },
        textAlign = textAlign
    )
}

class RichTextDecoration(
    val background: Color = Color.Transparent,
    val padding: Padding = Padding(),
    val borderRadius: BorderRadius = BorderRadius()
)

class BorderRadius(
    val topLeft: Float = 0f,
    val topRight: Float = 0f,
    val bottomLeft: Float = 0f,
    val bottomRight: Float = 0f
)

class Padding(
    val left: Float = 0f,
    val top: Float = 0f,
    val right: Float = 0f,
    val bottom: Float = 0f
)

/// 2024.06.21 이거 object로 만드니까 rebuild할 때 items가 초기화가 안되어서
/// rebuild할 때마다 위젯이 복사되는 문제가 있음 (items에 아이템을 계속 넣으니까)
class RichTextScope(
    val defaultTextSize: Float = 14F,
    val defaultTextColor: Color = Black100,
    val defaultFontFamily: FontFamily = Pretendard,
    val defaultFontWeight: FontWeight = FontWeight.Normal,
) {
    private val items = mutableListOf<MutableState<RichTextInstance>>()

    @Composable
    fun RichText(
        text: String,
        textStyle: CustomTextStyle = CustomTextStyle(
            fontSize = defaultTextSize,
            fontColor = defaultTextColor,
            fontFamily = defaultFontFamily,
            fontWeight = defaultFontWeight,
        ),
        endOfLine: Boolean = false,
        lineHeight: Dp = 0.dp,
        decoration: RichTextDecoration = RichTextDecoration()
    ) {
        val rt = remember {
            mutableStateOf(RichTextInstance())
        }

        val richText = RichTextInstance(
            text = text,
            textStyle = textStyle,
            endOfLine = endOfLine,
            lineHeight = lineHeight,
            decoration = decoration
        )

        if (rt !in items) {
            items.add(rt)
        }

        rt.value = richText
    }

    private class RichTextInstance(
        val text: String = "",
        val textStyle: CustomTextStyle = CustomTextStyle(),
        val endOfLine: Boolean = false,
        val lineHeight: Dp = 0.dp,
        val decoration: RichTextDecoration = RichTextDecoration()
    )


    fun richTextContent(): @Composable () -> Unit {
        return {
            items.forEach { state ->
                val it = state.value
                val text = it.text + if (it.endOfLine) "\n" else ""

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        // clip 다음에 background를 선언해줘야 색상이 선언된 후 clip이 적용됨
                        .clip(
                            RoundedCornerShape(
                                it.decoration.borderRadius.topLeft,
                                it.decoration.borderRadius.topRight,
                                it.decoration.borderRadius.bottomLeft,
                                it.decoration.borderRadius.bottomRight
                            )
                        )
                        .background(color = it.decoration.background)
                        .padding(
                            it.decoration.padding.left.dp,
                            it.decoration.padding.top.dp,
                            it.decoration.padding.right.dp,
                            it.decoration.padding.bottom.dp,
                        )
                ) {
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = text,
                        style = it.textStyle.style,
                        lineHeight = it.lineHeight.sp(),
                    )
                }
            }
        }
    }

    fun isEndOfLine(index: Int): Boolean {
        val item = items[index]
        return item.value.endOfLine
    }
}

@Composable
fun CustomRichText(
    defaultTextSize: Float = 14F,
    defaultTextColor: Color = Black100,
    defaultFontFamily: FontFamily = Pretendard,
    defaultFontWeight: FontWeight = FontWeight.Normal,
    textAlign: RichTextAlign = RichTextAlign.Start,
    textWidth: Dp = LocalConfiguration.current.screenWidthDp.dp,
    content: @Composable RichTextScope.() -> Unit
) {
    val scope = RichTextScope(
        defaultTextSize = defaultTextSize,
        defaultTextColor = defaultTextColor,
        defaultFontFamily = defaultFontFamily,
        defaultFontWeight = defaultFontWeight,
    )
    scope.content()
    val richContent = scope.richTextContent()

    return Layout(
        modifier = Modifier
            .wrapContentSize(),
        content = richContent,
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        var xPosition = 0
        var yPosition = 0

        val placeableMap = mutableMapOf<Int, Pair<Int, Int>>()
        var lineStartIndex = 0

        // 각각 라인의 너비를 구하고 layoutWidth를 구하기 위한 변수
        var lineWidth = 0
        // wrap content를 계산하기 위해 너비를 구하는 변수
        var layoutWidth = 0
        // 높이를 구하는 변수
        var layoutHeight = 0

        // 일단 컨텐츠 너비부터 구해줘야 뭘 할 수 있다.
        placeables.forEachIndexed { index, placeable ->
            lineWidth += placeable.width

            if (scope.isEndOfLine(index)) {
                layoutWidth = max(lineWidth, layoutWidth)
                lineWidth = 0
            }
        }

        layoutWidth = min(layoutWidth, textWidth.toPx().toInt())

        placeables.forEachIndexed { index, placeable ->
            layoutHeight += placeable.height

            // x좌표가 0이면 라인이 새로 시작되는 것
            if (xPosition == 0) {
                lineStartIndex = index
            }

            placeableMap[index] = Pair(xPosition, yPosition)
            val overflow = xPosition + placeable.width > layoutWidth

            println("[keykat] overflow: $overflow  xp: ${xPosition + placeable.width}  layout : $layoutWidth")
            // 라인이 끝나거나 마지막 라인일 경우
            if (overflow || scope.isEndOfLine(index) || index == placeables.lastIndex) {

                // 가운데 정렬
                if (textAlign == RichTextAlign.Center) {
                    val movePosition = layoutWidth / 2 - placeable.width / 2
                    for (i: Int in index..lineStartIndex) {
                        placeableMap[i]?.let {
                            placeableMap[i] = Pair(it.first + movePosition, it.second)
                        }
                    }
                }

                // 오른쪽 정렬
                else if (textAlign == RichTextAlign.End) {
                    val movePosition = layoutWidth - placeable.width
                    for (i: Int in index..lineStartIndex) {
                        placeableMap[i]?.let {
                            placeableMap[i] = Pair(it.first + movePosition, it.second)
                        }
                    }
                }

                // 좌측 정렬은 아무 작업 안하면 알아서 잘 된다.

                xPosition = 0
                yPosition += placeable.height
            } else {
                xPosition += placeable.width
            }
        }

        layout(layoutWidth, layoutHeight) {

            placeables.forEachIndexed { index, placeable ->
                val pair = placeableMap[index]
                pair?.let {
                    placeable.placeRelative(x = pair.first, y = pair.second)
                }
            }
        }
    }
}

enum class RichTextAlign {
    Start, End, Center
}

class CustomTextStyle(
    val fontSize: Float? = null,
    val fontColor: Color? = null,
    val fontWeight: FontWeight? = null,
    var fontFamily: FontFamily? = null
) {
    var style = TextStyle()

    init {
        style = TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize?.sp ?: run { 14F.sp },
            color = fontColor ?: run { Black100 },
        )
    }
}
