@extends('app')

@section('content')
<div class="container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">Home</div>

				<div class="panel-body">
					You are logged in!
				</div>
			</div>
		</div>

		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">Errors</div>
				{!! Form::open(array('url' => '/errors', 'class' => 'form-horizontal'))!!}
				<div class="panel-body form-group">
					@if(isset($err))
					@foreach ($err as $index => $errors) 
					<ul>
						@if ($errors->archive == 0)
						<li><input type="checkbox" name="archiver[]" value="{{$errors->id}}"> {{$errors->id}} {{$errors->Name}} {{$errors->Message}} {{$errors->assetcode}}</li>
						<?php $errorcount = 1 ?>
						@endif
					</ul>
					@endforeach
						<input class="btn-info" type="submit" value="Remove">
					@endif
				</div>
				{!! Form::close()!!}
			</div>
		</div>
	</div>
</div>
@endsection