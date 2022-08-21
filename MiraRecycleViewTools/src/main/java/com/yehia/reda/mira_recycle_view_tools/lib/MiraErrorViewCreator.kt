package com.yehia.reda.mira_recycle_view_tools.lib

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import com.yehia.reda.mira_recycle_view_tools.R
import com.yehia.reda.mira_recycle_view_tools.databinding.LayoutMiraErrorBinding
import com.yehia.reda.mira_recycle_view_tools.util.CallBack
import com.yehia.reda.mira_recycle_view_tools.util.Constant.GIF
import com.yehia.reda.mira_recycle_view_tools.util.Constant.OTHER
import com.yehia.reda.mira_recycle_view_tools.util.setTxtColor
import pl.droidsonroids.gif.GifDrawable

class MiraErrorViewCreator : RelativeLayout {

    private var context1: Context

    private var _binding: LayoutMiraErrorBinding? = null
    private val binding get() = _binding!!

    private var errorBackGround: Int = 0
    private var toggleShowError: Int = 2
    private var errorImage: Int = 0
    private var errorImageTypeTxt: String = ""
    private var errorTitle: String = ""
    private var errorTitleColor: Int = 0
    private var errorMessage: String = ""
    private var errorMessageColor: Int = 0
    private var actionText: String = ""
    private var actionTextColor: Int = 0
    private var actionBackGround: Int = 0
    private var font: Int = 0

    constructor(context: Context) : super(context) {
        this.context1 = context
        initView()
    }

    constructor(context: Context, parent: ViewGroup) : super(context) {
        this.context1 = context
        initView(parent = parent)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.context1 = context
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        this.context1 = context
        initView(attrs, defStyleAttr)
    }

    constructor(context: Context, attrs: AttributeSet, parent: ViewGroup) : super(context, attrs) {
        this.context1 = context
        initView(attrs, parent = parent)
    }

    private fun initView(
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        parent: ViewGroup? = null
    ) {
        val inflater = LayoutInflater.from(context1)
        if (_binding == null) {
            _binding = LayoutMiraErrorBinding.inflate(
                inflater, parent as ViewGroup?, false
            )
        }
        if (attrs != null) {
            setAttributes(attrs, defStyleAttr)
        } else {
            setUp()
        }
        if (parent != null) {
            parent.addView(_binding!!.root)
        } else {
            addView(_binding!!.root)
        }
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun setAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.MiraErrorViewCreator, defStyleAttr, 0
        )

        val attrsEnabled =
            typedArray.getBoolean(R.styleable.MiraErrorViewCreator_mira_attrs_error_enabled, false)

        if (attrsEnabled) {
            toggleShowError =
                typedArray.getInteger(R.styleable.MiraErrorViewCreator_mira_toggle_show_error, 2)

            errorBackGround =
                typedArray.getResourceId(
                    R.styleable.MiraErrorViewCreator_mira_error_back_ground_color,
                    0
                )
            errorImage =
                typedArray.getResourceId(R.styleable.MiraErrorViewCreator_mira_error_image, 0)
            val type =
                typedArray.getInteger(R.styleable.MiraErrorViewCreator_mira_error_image_type, 0)
            errorImageTypeTxt = if (type == 0) {
                OTHER
            } else {
                GIF
            }

            errorTitle = if (typedArray.getString(R.styleable.MiraErrorViewCreator_mira_error_title)
                    .isNullOrEmpty()
            ) {
                context1.getString(R.string.ooops)
            } else {
                typedArray.getString(R.styleable.MiraErrorViewCreator_mira_error_title)!!
            }
            errorTitleColor = typedArray.getResourceId(
                R.styleable.MiraErrorViewCreator_mira_error_title_txt_color, R.color.txt_title
            )

            errorMessage =
                if (typedArray.getString(R.styleable.MiraErrorViewCreator_mira_error_message)
                        .isNullOrEmpty()
                ) {
                    ""
                } else {
                    typedArray.getString(R.styleable.MiraErrorViewCreator_mira_error_message)!!
                }

            errorMessageColor = typedArray.getResourceId(
                R.styleable.MiraErrorViewCreator_mira_error_message_txt_color, R.color.txt_message
            )

            actionText =
                if (typedArray.getString(R.styleable.MiraErrorViewCreator_mira_error_action)
                        .isNullOrEmpty()
                ) {
                    ""
                } else {
                    typedArray.getString(R.styleable.MiraErrorViewCreator_mira_error_action)!!
                }

            actionTextColor = typedArray.getResourceId(
                R.styleable.MiraErrorViewCreator_mira_error_action_txt_color, R.color.white
            )
            actionBackGround = typedArray.getResourceId(
                R.styleable.MiraErrorViewCreator_mira_error_action_back_ground, R.drawable.shape_btn
            )

            font = typedArray.getResourceId(
                R.styleable.MiraErrorViewCreator_mira_error_font, R.font.urw_din_bold
            )

            setErrorData()
        } else {
            setUp()
        }
    }

    private fun setUp(
        errorBackGround: Int = 0,
        toggleShowError: Int = 2,
        errorImage: Int = R.drawable.ic_no_connection_vector,
        errorImageTypeTxt: String = OTHER,
        errorTitle: String = "",
        errorTitleColor: Int = R.color.txt_message,
        errorMessage: String = "",
        errorMessageColor: Int = R.color.txt_message,
        actionText: String = "",
        actionTextColor: Int = R.color.white,
        actionBackGround: Int = R.drawable.shape_btn,
        font: Int = R.font.urw_din_bold,
    ) {
        this.errorBackGround = errorBackGround
        this.toggleShowError = toggleShowError
        this.errorImage = errorImage
        this.errorImageTypeTxt = errorImageTypeTxt
        this.errorTitle = errorTitle
        this.errorTitleColor = errorTitleColor
        this.errorMessage = errorMessage
        this.errorMessageColor = errorMessageColor
        this.actionText = actionText
        this.actionTextColor = actionTextColor
        this.actionBackGround = actionBackGround
        this.font = font

        setErrorData()
    }

    fun reset() {
        errorBackGround = 0
        toggleShowError = 2
        errorImage = R.drawable.ic_no_connection_vector
        errorImageTypeTxt = OTHER
        errorTitle = ""
        errorTitleColor = R.color.txt_message
        errorMessage = ""
        errorMessageColor = R.color.txt_message
        actionText = ""
        actionTextColor = R.color.white
        actionBackGround = R.drawable.shape_btn
        font = R.font.urw_din_bold

        setErrorData()
    }

    private fun setErrorData() {
        if (errorBackGround != 0) {
            binding.rlError.setBackgroundColor(ContextCompat.getColor(context1, errorBackGround))
        }
        if (errorImage != 0) {
            when (errorImageTypeTxt) {
                OTHER -> binding.ivErrorImage.setImageResource(errorImage)
                else -> {
                    val gifFromResource = GifDrawable(context1.resources, errorImage)
                    binding.givErrorImage.setImageDrawable(gifFromResource)
                }
            }
        }

        binding.tvErrorTitle.text = errorTitle.ifEmpty {
            context1.getString(R.string.ooops)
        }
        binding.tvErrorTitle.setTxtColor(errorTitleColor)
        if (errorMessage.isNotEmpty()) {
            binding.tvErrorMessage.text = errorMessage
        }
        binding.tvErrorMessage.setTxtColor(errorMessageColor)

        if (actionText.isNotEmpty()) {
            binding.btnErrorAction.text = actionText
            binding.btnErrorAction.setTxtColor(actionTextColor)
            if (actionBackGround != 0) {
                binding.btnErrorAction.setBackgroundResource(actionBackGround)
            }
        } else {
            binding.btnErrorAction.isGone = true
        }

        val typeface: Typeface = ResourcesCompat.getFont(context, font)!!
        binding.tvErrorTitle.typeface = typeface
        binding.tvErrorMessage.typeface = typeface
        binding.btnErrorAction.typeface = typeface

        toggleShowError(toggleShowError)
    }

    fun toggleShowError(visibility: Int) {
        binding.rlError.isGone = when (visibility) {
            VISIBLE -> false
            else -> true
        }
    }

    fun changeData(
        errorImage: Int = R.drawable.ic_no_connection_vector,
        errorImageTypeTxt: String = OTHER,
        errorTitle: String = "",
        errorMessage: String = "",
        actionText: String = "",
    ) {
        if (errorImage != 0) {
            when (errorImageTypeTxt) {
                OTHER -> binding.ivErrorImage.setImageResource(errorImage)
                else -> {
                    val gifFromResource = GifDrawable(context1.resources, errorImage)
                    binding.givErrorImage.setImageDrawable(gifFromResource)
                }
            }
        }

        binding.tvErrorTitle.text = errorTitle.ifEmpty {
            context1.getString(R.string.ooops)
        }
        if (errorMessage.isNotEmpty()) {
            binding.tvErrorMessage.text = errorMessage
        }
        if (errorTitle.isNotEmpty()) {
            binding.btnErrorAction.text = actionText
        } else {
            binding.btnErrorAction.isGone = true
        }
    }

    fun returnToStartData() {
        setErrorData()
    }

    fun onErrorBtnClick(action: () -> Unit) {
        binding.btnErrorAction.setOnClickListener {
            action()
        }
    }
}